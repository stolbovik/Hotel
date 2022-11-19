package org.stolbovik.database.hotel.service;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.StatusOfCleaningRequest;
import org.stolbovik.database.hotel.repository.StatusOfCleaningRequestRepository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class StatusOfCleaningRequestService {

    private final StatusOfCleaningRequestRepository statusOfCleaningRequestRepository;
    private final Statement statement;

    public StatusOfCleaningRequestService(@NotNull Statement statement) {
        this.statusOfCleaningRequestRepository = new StatusOfCleaningRequestRepository();
        this.statement = statement;
    }

    public List<StatusOfCleaningRequest> getStatusOfCleaningRequests() throws SQLException {
        String query = "select * from [Статус заявки уборки]";
        Optional<List<StatusOfCleaningRequest>> list =  statusOfCleaningRequestRepository
                                                        .readStatusOfCleaningRequest(statement, query);
        if (list.isPresent()) {
            return list.get();
        }
        throw new SQLException("Таблица \"Статус заявки уборки\" пуста\n");
    }

    public StatusOfCleaningRequest getStatusOfCleaningRequestsByName(@NotNull String name)
            throws SQLException {
        String query = "select * from [Статус заявки уборки] where Статус = '" + name + "'";
        Optional<List<StatusOfCleaningRequest>> list =  statusOfCleaningRequestRepository
                .readStatusOfCleaningRequest(statement, query);
        if (list.isPresent()) {
            return list.get().get(0);
        }
        throw new SQLException("Такого статуса уборки не существует");
    }

}
