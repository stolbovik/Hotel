package org.stolbovik.database.hotel.repository;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.RequestForCleaning;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RequestForCleaningRepository {

    public Optional<List<RequestForCleaning>> readRequestForCleaning(@NotNull Statement statement,
                                                                     @NotNull String query) throws SQLException {
        ResultSet resultSet = statement.executeQuery(query);
        if (!resultSet.next()) {
            return Optional.empty();
        }
        List<RequestForCleaning> requestForCleanings = new ArrayList<>();
        do {
            RequestForCleaning requestForCleaning = new RequestForCleaning();
            requestForCleaning.setId(resultSet.getInt("id"));
            requestForCleaning.setIdOfBooking(resultSet.getInt("Номер бронирования"));
            requestForCleaning.setIdOfStatusRequest(resultSet.getInt("Статус заявки"));
            requestForCleaning.setDateOfRequest(resultSet.getDate("Дата заявки"));
            requestForCleaning.setIdOfEmployee(resultSet.getInt("Выполняющий"));
            requestForCleanings.add(requestForCleaning);
        } while (resultSet.next());
        return Optional.of(requestForCleanings);
    }

    public int updateRequestForCleaning(@NotNull Statement statement,
                                        @NotNull String query) throws SQLException {
        return statement.executeUpdate(query);
    }

}
