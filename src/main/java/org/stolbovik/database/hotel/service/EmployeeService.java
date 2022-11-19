package org.stolbovik.database.hotel.service;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Employee;
import org.stolbovik.database.hotel.models.Post;
import org.stolbovik.database.hotel.repository.EmployeeRepository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final Statement statement;

    public EmployeeService(@NotNull Statement statement) {
        this.employeeRepository = new EmployeeRepository();
        this.statement = statement;
    }

    public List<Employee> getFreeEmployeeByJobTitle(@NotNull Post jobTitle) throws SQLException {
        String query = "select * from Сотрудники where [ID должности] = " + jobTitle.getId() +
                " and Занят = 0";
        Optional<List<Employee>> list = employeeRepository.readEmployees(statement, query);
        if (list.isPresent()) {
            return list.get();
        }
        throw new SQLException("Нет свободных сотрудников этой должности\n");
    }

    public boolean addNewEmployee(@NotNull String firstName,
                                  @NotNull String lastName,
                                  @NotNull String fatherName,
                                  @NotNull String passport,
                                  @NotNull Post post,
                                  @NotNull Date birthday) throws SQLException {
        String query = "insert into Сотрудники (ФИО, Паспорт, [Дата рождения], [ID Должности]) " +
                "values ('" + firstName + " " + lastName + fatherName + "', " + passport
                + ", " + post.getId() + ")";
        int res = employeeRepository.updateEmployee(statement, query);
        if (res != 1) {
            throw new SQLException("Не удалось добавить сотрудника в базу");
        }
        return true;
    }
}
