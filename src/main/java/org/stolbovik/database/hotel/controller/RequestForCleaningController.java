package org.stolbovik.database.hotel.controller;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.*;
import org.stolbovik.database.hotel.service.*;
import org.stolbovik.database.hotel.utils.HelpFunction;

import java.sql.SQLException;
import java.sql.Statement;;

public class RequestForCleaningController {

    private final RequestForCleaningService requestForCleaningService;
    private final EmployeeService employeeService;
    private final PostService postService;
    private final StatusOfCleaningRequestService statusOfCleaningRequestService;
    private final BookingService bookingService;
    private final RoomService roomService;
    private final EquipmentService equipmentService;

    public RequestForCleaningController(@NotNull Statement statement) {
        this.requestForCleaningService = new RequestForCleaningService(statement);
        this.employeeService = new EmployeeService(statement);
        this.postService = new PostService(statement);
        this.statusOfCleaningRequestService = new StatusOfCleaningRequestService(statement);
        this.bookingService = new BookingService(statement);
        this.roomService = new RoomService(statement);
        this.equipmentService = new EquipmentService(statement);
    }

    public void assignEmployeeToRequest(int id) throws SQLException, IllegalArgumentException {
        RequestForCleaning requestForCleaning = requestForCleaningService.getRequestById(id);
        if (requestForCleaning.getIdOfStatusRequest() == 2) {
            throw new IllegalArgumentException("Заявка выполняется");
        }
        if (requestForCleaning.getIdOfStatusRequest() == 3) {
            throw new IllegalArgumentException("Заявка уже выполнена");
        }
        Post post = postService.getPostByName("Уборщик");
        Employee employee = employeeService.getFreeEmployeeByPost(post).get(0);
        requestForCleaningService.assignAnEmployee(employee, requestForCleaning);
        employeeService.setStatusOfEmployment(employee, true);
    }

    public void finishCleaningRoom(int id) throws SQLException {
        RequestForCleaning requestForCleaning = requestForCleaningService.getRequestById(id);
        StatusOfCleaningRequest statusOfCleaningRequest =
                statusOfCleaningRequestService.getStatusOfCleaningRequestsByName("Выполнена");
        requestForCleaningService.setStatusOfRequest(requestForCleaning, statusOfCleaningRequest);
        Booking booking = bookingService.getBookingById(requestForCleaning.getId());
        Room room = roomService.getRoomByID(booking.getIdOfRoom());
        Employee employee = employeeService.getEmployeeById(requestForCleaning.getIdOfEmployee());
        roomService.setCleaningStatus(room, true);
        employeeService.setStatusOfEmployment(employee, false);
    }

    public void assignEquipmentToEmployee(@NotNull String equipment,
                                          @NotNull String passport) throws SQLException, IllegalArgumentException {
        HelpFunction.checkPassport(passport);
        Employee employee = employeeService.getEmployeeByPassport(passport);
        if (employee.getIdOfEquipment() != null) {
            throw new IllegalArgumentException("Сотрудник уже владеет спец.оборудованием");
        }
        Equipment equipment1 = equipmentService.getFreeEquipmentByName(equipment).get(0);
        employeeService.setEquipment(employee, equipment1);
        equipmentService.setStatusOfEmployment(equipment1, true);
    }

    public void releaseEmployeeFromEquipment(@NotNull String passport) throws SQLException, IllegalArgumentException {
        HelpFunction.checkPassport(passport);
        Employee employee = employeeService.getEmployeeByPassport(passport);
        if (employee.getIdOfEquipment() == null) {
            throw new IllegalArgumentException("Сотрудник не владеет спец.оборудованием");
        }
        Equipment equipment = equipmentService.getEquipmentById(employee.getIdOfEquipment());
        employeeService.deleteEquipment(employee);
        equipmentService.setStatusOfEmployment(equipment, false);
    }

}
