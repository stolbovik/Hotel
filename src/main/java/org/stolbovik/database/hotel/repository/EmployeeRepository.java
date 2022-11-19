package org.stolbovik.database.hotel.repository;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository {

    public Optional<List<Employee>> readEmployees(@NotNull Statement statement,
                                                  @NotNull String query) throws SQLException {
        ResultSet resultSet = statement.executeQuery(query);
        if (!resultSet.next()) {
            return Optional.empty();
        }
        List<Employee> employees = new ArrayList<>();
        do {
            Employee employee = new Employee();
            employee.setId(resultSet.getInt("id"));
            employee.setFIO(resultSet.getString("ФИО"));
            employee.setPassport(resultSet.getLong("Паспорт"));
            employee.setBirthday(resultSet.getDate("Дата рождения"));
            employee.setIdOFPost(resultSet.getInt("ID должности"));
            employee.setStatusOfEmployment(resultSet.getBoolean("Занят"));
            employee.setIdOfEquipment(resultSet.getInt("ID Спец.оборудования"));
            employee.setPrize(resultSet.getInt("Прибавка"));
            employees.add(employee);
        } while (resultSet.next());
        return Optional.of(employees);
    }

    public int updateEmployee(@NotNull Statement statement,
                              @NotNull String query) throws SQLException {
        return statement.executeUpdate(query);
    }

}
