package org.stolbovik.database.hotel.controller;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Employee;
import org.stolbovik.database.hotel.models.Equipment;
import org.stolbovik.database.hotel.models.Post;
import org.stolbovik.database.hotel.service.EmployeeService;
import org.stolbovik.database.hotel.service.EquipmentService;
import org.stolbovik.database.hotel.service.PostService;
import org.stolbovik.database.hotel.utils.HelpFunction;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class EmployeeController {

    private final EmployeeService employeeService;
    private final PostService postService;
    private final EquipmentService equipmentService;

    public EmployeeController() {
        this.employeeService = new EmployeeService();
        this.postService = new PostService();
        this.equipmentService = new EquipmentService();
    }

    public void hireNewEmployee(@NotNull String passport, @NotNull String firstName, @NotNull String lastName,
                                @NotNull String fatherName, @NotNull Date birthday, @NotNull String post)
            throws SQLException, IllegalArgumentException {
        HelpFunction.checkAdulthood(birthday);
        HelpFunction.checkName(fatherName);
        HelpFunction.checkName(firstName);
        HelpFunction.checkName(lastName);
        HelpFunction.checkPassport(passport);
        List<Employee> list = employeeService.getEmployees();
        for (Employee employee: list) {
            if(employee.getPassport() == Long.parseLong(passport)) {
                throw new IllegalArgumentException("Сотрудник с такими паспортными данными уже есть");
            }
        }
        Post post1 = postService.getPostByName(post);
        if (post1.getCountOfRequiredEmployees() == 0) {
            throw new IllegalArgumentException("Нам сейчас не нужны работники на данную должность");
        }
        employeeService.addNewEmployee(firstName, lastName, fatherName, passport, post1, birthday);
        postService.decrementCountOfRequiredEmployees(post1);
    }

    public void fireAnEmployee(@NotNull String passport) throws SQLException, IllegalArgumentException {
        HelpFunction.checkPassport(passport);
        Employee employee = employeeService.getEmployeeByPassport(passport);
        Post post = postService.getPostById(employee.getIdOFPost());
        employeeService.deleteEmployee(employee);
        postService.incrementCountOfRequiredEmployees(post);
    }

    public int calculateSalariesForTime(@NotNull Date start,
                                        @NotNull Date end) throws SQLException, IllegalArgumentException {
        HelpFunction.checkDates(start, end);
        return employeeService.getSumSalary(start, end);
    }

    public void changePrize(@NotNull String passport,
                            int prize,
                            @NotNull String options) throws SQLException, IllegalArgumentException {
        if (options.equalsIgnoreCase("повысить")) {
            raisePrize(passport, prize);
        } else if (options.equalsIgnoreCase("понизить")) {
            reducePrize(passport, prize);
        } else {
            throw new IllegalArgumentException("Неверное ключевое слово");
        }

    }

    public void raisePrize(@NotNull String passport,
                           int prize) throws SQLException, IllegalArgumentException {
        HelpFunction.checkPassport(passport);
        Employee employee = employeeService.getEmployeeByPassport(passport);
        employeeService.increaseOfPrize(employee, prize);
    }

    public void reducePrize(@NotNull String passport,
                           int prize) throws SQLException, IllegalArgumentException {
        HelpFunction.checkPassport(passport);
        Employee employee = employeeService.getEmployeeByPassport(passport);
        employeeService.decreaseOfPrize(employee, prize);
    }

    public void assignEquipmentToEmployee(@NotNull String equipment,
                                          @NotNull String passport) throws SQLException, IllegalArgumentException {
        HelpFunction.checkPassport(passport);
        Employee employee = employeeService.getEmployeeByPassport(passport);
        if (employee.getIdOfEquipment() != 0) {
            throw new IllegalArgumentException("Сотрудник владеет оборудованием");
        }
        Equipment equipment1 = equipmentService.getFreeEquipmentByName(equipment).get(0);
        employeeService.setEquipment(employee, equipment1);
        equipmentService.setStatusOfEmployment(equipment1, true);
    }

    public void releaseEmployeeFromEquipment(@NotNull String passport) throws SQLException, IllegalArgumentException {
        HelpFunction.checkPassport(passport);
        Employee employee = employeeService.getEmployeeByPassport(passport);
        if (employee.getIdOfEquipment() == 0) {
            throw new IllegalArgumentException("Сотрудник не владеет спец.оборудованием");
        }
        Equipment equipment = equipmentService.getEquipmentById(employee.getIdOfEquipment());
        employeeService.deleteEquipment(employee);
        equipmentService.setStatusOfEmployment(equipment, false);
    }

}
