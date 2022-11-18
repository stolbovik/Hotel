package org.stolbovik.database.hotel.repository;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.ServiceWithEmployee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceWithEmployeeRepository {

    public Optional<List<ServiceWithEmployee>> readServiceWithEmployee(@NotNull Statement statement,
                                                                              @NotNull String query) throws SQLException {
        ResultSet resultSet = statement.executeQuery(query);
        if (!resultSet.next()) {
            return Optional.empty();
        }
        List<ServiceWithEmployee> serviceWithEmployees = new ArrayList<>();
        do {
            ServiceWithEmployee serviceWithEmployee = new ServiceWithEmployee();
            serviceWithEmployee.setId(resultSet.getInt("id"));
            serviceWithEmployee.setIdService(resultSet.getInt("id услуги"));
            serviceWithEmployee.setIdEmployee(resultSet.getInt("id работника"));
            serviceWithEmployees.add(serviceWithEmployee);
        } while (resultSet.next());
        return Optional.of(serviceWithEmployees);
    }

    public int updateServiceWithEmployee(@NotNull Statement statement,
                                                @NotNull String query) throws SQLException {
        return statement.executeUpdate(query);
    }

}
