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

    public RequestForCleaningController() {
        this.requestForCleaningService = new RequestForCleaningService();
        this.employeeService = new EmployeeService();
        this.postService = new PostService();
        this.statusOfCleaningRequestService = new StatusOfCleaningRequestService();
        this.bookingService = new BookingService();
        this.roomService = new RoomService();
        this.equipmentService = new EquipmentService();
    }

    public void assignEmployeeToRequest(int id) throws SQLException, IllegalArgumentException {
        RequestForCleaning requestForCleaning = requestForCleaningService.getRequestById(id);
        if (requestForCleaning.getIdOfStatusRequest() == 2) {
            throw new IllegalArgumentException("Заявка уже выполняется");
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
        Booking booking = bookingService.getBookingById(requestForCleaning.getIdOfBooking());
        Room room = roomService.getRoomByID(booking.getIdOfRoom());
        Employee employee = employeeService.getEmployeeById(requestForCleaning.getIdOfEmployee());
        roomService.setCleaningStatus(room, true);
        employeeService.setStatusOfEmployment(employee, false);
    }

}
