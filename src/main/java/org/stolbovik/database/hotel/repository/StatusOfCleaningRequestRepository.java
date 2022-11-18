package org.stolbovik.database.hotel.repository;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.StatusOfCleaningRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StatusOfCleaningRequestRepository {

    public Optional<List<StatusOfCleaningRequest>> readStatusOfCleaningRequest(@NotNull Statement statement,
                                                                               @NotNull String query) throws SQLException {
        ResultSet resultSet = statement.executeQuery(query);
        if (!resultSet.next()) {
            return Optional.empty();
        }
        List<StatusOfCleaningRequest> statusOfCleaningRequests = new ArrayList<>();
        do {
            StatusOfCleaningRequest statusOfCleaningRequest = new StatusOfCleaningRequest();
            statusOfCleaningRequest.setId(resultSet.getInt("id"));
            statusOfCleaningRequest.setStatus(resultSet.getString("Статус"));
            statusOfCleaningRequests.add(statusOfCleaningRequest);
        } while (resultSet.next());
        return Optional.of(statusOfCleaningRequests);
    }

    public int updateStatusOfCleaningRequest(@NotNull Statement statement,
                                             @NotNull String query) throws SQLException {
        return statement.executeUpdate(query);
    }

}
