package org.stolbovik.database.hotel.service;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.*;
import org.stolbovik.database.hotel.repository.RequestForCleaningRepository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class RequestForCleaningService {

    private final RequestForCleaningRepository requestForCleaningRepository;
    private final Statement statement;

    public RequestForCleaningService(Statement statement) {
        this.requestForCleaningRepository = new RequestForCleaningRepository();
        this.statement = statement;
    }

    public boolean addRequest(@NotNull Booking booking,
                              @NotNull Employee employee,
                              @NotNull StatusOfCleaningRequest statusOfCleaningRequest) throws SQLException {
        String query = "insert into [Заявки на уборку] ([Номер бронирования], [Статус заявки], " +
                "[Дата заявки], Выполняющий) values (" +
                booking.getId() + ", " + statusOfCleaningRequest.getId() + ", " +
                java.sql.Date.valueOf(String.valueOf(new Date())) + ", " +
                employee.getId() + ")";
        int res = requestForCleaningRepository.updateRequestForCleaning(statement, query);
        if (res == 1) {
            return true;
        } else {
            throw new SQLException("Не удалось добавить заявки на бронирование");
        }
    }

}
