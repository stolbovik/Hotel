package org.stolbovik.database.hotel.service;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.*;
import org.stolbovik.database.hotel.repository.RequestForCleaningRepository;
import org.stolbovik.database.hotel.repository.StatusOfCleaningRequestRepository;
import org.stolbovik.database.hotel.utils.Constatns;
import org.stolbovik.database.hotel.utils.HelpFunction;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class RequestForCleaningService {

    private final RequestForCleaningRepository requestForCleaningRepository;
    private final StatusOfCleaningRequestRepository statusOfCleaningRequestRepository;
    private final Statement statement;

    public RequestForCleaningService() {
        this.requestForCleaningRepository = new RequestForCleaningRepository();
        this.statusOfCleaningRequestRepository = new StatusOfCleaningRequestRepository();
        this.statement = Constatns.STATEMENT;
    }

    public Optional<List<RequestForCleaning>> checkRequestForBookingAndStatus(@NotNull Booking booking,
                                                   @NotNull StatusOfCleaningRequest statusOfCleaningRequest) throws SQLException {
        String query = "select * from [Заявки на уборку] where [Номер бронирования] = " + booking.getId()
                + " and [Статус заявки] = " + statusOfCleaningRequest.getId();
        return requestForCleaningRepository.readRequestForCleaning(statement, query);
    }

    public RequestForCleaning getRequestById(int id) throws SQLException {
        String query = "select * from [Заявки на уборку] where ID = " + id;
        Optional<List<RequestForCleaning>> list = requestForCleaningRepository
                .readRequestForCleaning(statement, query);
        if(list.isEmpty()) {
            throw new IllegalArgumentException("Такой заявки не существует");
        }
        return list.get().get(0);
    }

    public boolean addRequest(@NotNull Booking booking,
                              @NotNull Employee employee,
                              @NotNull StatusOfCleaningRequest statusOfCleaningRequest)
                            throws SQLException, IllegalArgumentException {
        String query = "select * from [Статус заявки уборки] where Статус = 'В очереди'";
        StatusOfCleaningRequest statusOfCleaningRequest1 =
                statusOfCleaningRequestRepository.readStatusOfCleaningRequest(statement, query).get().get(0);
        if (checkRequestForBookingAndStatus(booking, statusOfCleaningRequest1).isPresent()) {
            throw new IllegalArgumentException("Заявка на уборку данного номера уже есть");
        }
        query = "select * from [Статус заявки уборки] where Статус = 'Выполняется'";
        statusOfCleaningRequest1 =  statusOfCleaningRequestRepository.
                                    readStatusOfCleaningRequest(statement, query).get().get(0);
        if (checkRequestForBookingAndStatus(booking, statusOfCleaningRequest1).isPresent()) {
            throw new IllegalArgumentException("Заявка на уборку данного номера уже есть");
        }
        query = "insert into [Заявки на уборку] ([Номер бронирования], [Статус заявки], " +
                "[Дата заявки], Выполняющий) values (" +
                booking.getId() + ", " + statusOfCleaningRequest.getId() + ", '" +
                HelpFunction.dateToSqlDate(new Date()) + "', " +
                employee.getId() + ")";
        int res = requestForCleaningRepository.updateRequestForCleaning(statement, query);
        if (res == 1) {
            return true;
        } else {
            throw new SQLException("Не удалось добавить заявку на бронирование");
        }
    }

    public void addRequest(@NotNull Booking booking) throws SQLException, IllegalArgumentException {
        String query = "select * from [Статус заявки уборки] where Статус = 'В очереди'";
        StatusOfCleaningRequest statusOfCleaningRequest1 =
                statusOfCleaningRequestRepository.readStatusOfCleaningRequest(statement, query).get().get(0);
        if (checkRequestForBookingAndStatus(booking, statusOfCleaningRequest1).isPresent()) {
            throw new IllegalArgumentException("Заявка на уборку данного номера уже есть");
        }
        query = "select * from [Статус заявки уборки] where Статус = 'Выполняется'";
        statusOfCleaningRequest1 =  statusOfCleaningRequestRepository.
                readStatusOfCleaningRequest(statement, query).get().get(0);
        if (checkRequestForBookingAndStatus(booking, statusOfCleaningRequest1).isPresent()) {
            throw new IllegalArgumentException("Заявка на уборку данного номера уже есть");
        }
        query = "insert into [Заявки на уборку] ([Номер бронирования], [Статус заявки], " +
                "[Дата заявки]) values (" + booking.getId() + ", 1, '" +
                HelpFunction.dateToSqlDate(new Date()) + "')";
        int res = requestForCleaningRepository.updateRequestForCleaning(statement, query);
        if (res != 1) {
            throw new SQLException("Не удалось добавить заявку на бронирование");
        }
    }

    public void assignAnEmployee(@NotNull Employee employee,
                                 @NotNull RequestForCleaning requestForCleaning)
                                    throws SQLException, IllegalArgumentException {
        if (requestForCleaning.getIdOfEmployee() != 0) {
            throw new IllegalArgumentException("За уборку уже назначен работник");
        }
        if (employee.getStatusOfEmployment()) {
            throw new IllegalArgumentException("Этот раотник сейчас занят");
        }
        String query = "update [Заявки на уборку] set Выполняющий = " + employee.getId() +
                ", [Статус заявки] = 2 where ID = " + requestForCleaning.getId();
        int res = requestForCleaningRepository.updateRequestForCleaning(statement, query);
        if (res!= 1) {
            throw new SQLException("Не удалось назначить сотрудника на уборку");
        }
    }

    public boolean setStatusOfRequest(@NotNull RequestForCleaning requestForCleaning,
                                      @NotNull StatusOfCleaningRequest statusOfCleaningRequest) throws SQLException {
        String query =  "update [Заявки на уборку] set [Статус заявки] = " + statusOfCleaningRequest.getId()
                        + " where ID = " + requestForCleaning.getId();
        int res = requestForCleaningRepository.updateRequestForCleaning(statement, query);
        if (res != 1) {
            throw new SQLException("Не удалось изменить статус заявки на уборку");
        }
        return true;
    }

}
