package org.stolbovik.database.hotel.repository;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.PaidService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaidServiceRepository {

    public Optional<List<PaidService>> readPaidServices(@NotNull Statement statement,
                                                        @NotNull String query) throws SQLException {
        ResultSet resultSet = statement.executeQuery(query);
        if (!resultSet.next()) {
            return Optional.empty();
        }
        List<PaidService> paidServices = new ArrayList<>();
        do {
            PaidService paidService = new PaidService();
            paidService.setId(resultSet.getInt("id"));
            paidService.setName(resultSet.getString("Название"));
            paidService.setPrice(resultSet.getInt("Цена"));
            paidService.setTimeForService(resultSet.getFloat("Время выполнения (часы)"));
            paidServices.add(paidService);
        } while (resultSet.next());
        return Optional.of(paidServices);
    }

    public int updatePaidService(@NotNull Statement statement,
                                 @NotNull String query) throws SQLException {
        return statement.executeUpdate(query);
    }

}
