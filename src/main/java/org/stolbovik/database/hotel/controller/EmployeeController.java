package org.stolbovik.database.hotel.controller;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Employee;
import org.stolbovik.database.hotel.models.Post;
import org.stolbovik.database.hotel.service.EmployeeService;
import org.stolbovik.database.hotel.service.PostService;
import org.stolbovik.database.hotel.utils.HelpFunction;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class EmployeeController {

    private final EmployeeService employeeService;
    private final PostService postService;

    public EmployeeController(@NotNull Statement statement) {
        this.employeeService = new EmployeeService(statement);
        this.postService = new PostService(statement);
    }

    public void hireNewEmployee(@NotNull String passport, @NotNull String firstName, @NotNull String lastName,
                                @NotNull String fatherName, @NotNull Date birthday, @NotNull String post)
            throws SQLException, IllegalArgumentException {
        HelpFunction.checkAdulthood(birthday);
        HelpFunction.checkName(fatherName);
        HelpFunction.checkName(firstName);
        HelpFunction.checkName(lastName);
        HelpFunction.checkPassport(passport);
        Post post1 = postService.getPostByName(post);
        if (post1.getCountOfRequiredEmployees() == 0) {
            throw new IllegalArgumentException("Нам сейчас не нужны работники на данную должность");
        }
        employeeService.addNewEmployee(firstName, lastName, fatherName, passport, post1, birthday);
        postService.decrementCountOfRequiredEmployees(post1);
    }

    public void fireAnEmployee(@NotNull String passport) throws SQLException, IllegalArgumentException {
        Employee employee = employeeService.getEmployeeByPassport(passport);
        employeeService.deleteEmployee(employee);
        Post post = postService.getPostById(employee.getIdOFPost());
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
        if (options.equalsIgnoreCase("поднять")) {
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

}
