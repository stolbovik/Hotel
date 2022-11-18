package org.stolbovik.database.hotel.repository;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.OrderedService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderedServiceRepository {

    public Optional<List<OrderedService>> readOrderedServices(@NotNull Statement statement,
                                                                     @NotNull String query) throws SQLException {
        ResultSet resultSet = statement.executeQuery(query);
        if (!resultSet.next()) {
            return Optional.empty();
        }
        List<OrderedService> orderedServices = new ArrayList<>();
        do {
            OrderedService orderedService = new OrderedService();
            orderedService.setId(resultSet.getInt("id"));
            orderedService.setServiceWithEmployee(resultSet.getInt("Услуга и исполнитель"));
            orderedService.setDate(resultSet.getDate("Дата"));
            orderedService.setIdOfRoom(resultSet.getInt("ID комнаты"));
            orderedServices.add(orderedService);
        } while (resultSet.next());
        return Optional.of(orderedServices);
    }

    public int updateOrderedService(@NotNull Statement statement,
                                           @NotNull String query) throws SQLException {
        return statement.executeUpdate(query);
    }

}
