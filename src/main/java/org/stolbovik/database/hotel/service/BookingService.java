package org.stolbovik.database.hotel.service;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Booking;
import org.stolbovik.database.hotel.models.Client;
import org.stolbovik.database.hotel.models.Room;
import org.stolbovik.database.hotel.repository.BookingRepository;
import org.stolbovik.database.hotel.repository.ClientRepository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class BookingService {

    private final BookingRepository bookingRepository;
    private final ClientRepository clientRepository;
    private final Statement statement;

    public BookingService(Statement statement) {
        this.bookingRepository = new BookingRepository();
        this.clientRepository = new ClientRepository();
        this.statement = statement;
    }

    public List<Booking> getBookings() throws SQLException {
        String query = "select * from Бронирование";
        Optional<List<Booking>> list = bookingRepository.readBookings(statement, query);
        if (list.isPresent()) {
            return list.get();
        }
        throw new SQLException("Таблица \"Бронирование\" пуста\n");
    }

    public boolean addBooking(  @NotNull Date start,
                                @NotNull Date end,
                                @NotNull Client client,
                                @NotNull Room room,
                          int paymentState) throws SQLException {
        String query =  "insert into Бронирование (Прибывание, Выезд, [Статус заселения]," +
                        " [Статус оплаты], [ID Клиента], [Номер комнаты]) values (" +
                        java.sql.Date.valueOf(String.valueOf(start)) + ", " +
                        java.sql.Date.valueOf(String.valueOf(end)) + ", 0, " + paymentState +
                        ", " + client.getId() + ", " + room.getId() + ")";
        int res = bookingRepository.updateBooking(statement, query);
        if (res == 1) {
            return true;
        } else {
            throw new SQLException("Не удалось добавить бронь");
        }
    }

    public Optional<Booking> getTodayStayBookingByPassport(@NotNull String passport) throws SQLException {
        String query = "select * from Клиенты where Паспорт = " + passport;
        Optional<List<Client>> list = clientRepository.readClients(statement, query);
        if (list.isEmpty()) {
            throw new SQLException("Данного клиента нет в базе данных");
        }
        Client client = list.get().get(0);
        query = "select * from Бронирование where [ID Клиента] = " + client.getId() + " AND " +
                "Прибывание = " + java.sql.Date.valueOf(String.valueOf(new Date()));
        Optional<List<Booking>> list2 = bookingRepository.readBookings(statement, query);
        if (list2.isEmpty()) {
            throw new IllegalArgumentException("Брони на сегодня на данного клиента нет");
        }
        return Optional.of(list2.get().get(0));
    }

    public Optional<Booking> getTodayBookingByPassport(@NotNull String passport) throws SQLException {
        String query = "select * from Клиенты where Паспорт = " + passport;
        Optional<List<Client>> list = clientRepository.readClients(statement, query);
        if (list.isEmpty()) {
            throw new SQLException("Данного клиента нет в базе данных");
        }
        Client client = list.get().get(0);
        query = "select * from Бронирование where [ID Клиента] = " + client.getId() + " AND " +
                "Прибывание <= " + java.sql.Date.valueOf(String.valueOf(new Date())) +
                " AND Выезд >= " + java.sql.Date.valueOf(String.valueOf(new Date()));
        Optional<List<Booking>> list2 = bookingRepository.readBookings(statement, query);
        if (list2.isEmpty()) {
            throw new IllegalArgumentException("Брони на сегодня на данного клиента нет");
        }
        return Optional.of(list2.get().get(0));
    }

    public boolean setPaymentStatus(@NotNull Booking booking, boolean status) throws SQLException {
        int temp;
        if (status) {
            temp = 1;
        } else {
            temp = 0;
        }
        String query = "update Бронирование set [Статус оплаты] = " + temp + " where ID = " + booking.getId();
        int res = bookingRepository.updateBooking(statement, query);
        if (res != 1) {
            throw new SQLException("Не удалось изменить статус оплаты у данного бронирования");
        }
        return true;
    }

    public boolean setSettlementStatus(@NotNull Booking booking, boolean status) throws SQLException {
        int temp;
        if (status) {
            temp = 1;
        } else {
            temp = 0;
        }
        String query = "update Бронирование set [Статус заселения] = " + temp + " where ID = " + booking.getId();
        int res = bookingRepository.updateBooking(statement, query);
        if (res != 1) {
            throw new SQLException("Не удалось изменить статус заселения у данного бронирования");
        }
        return true;
    }

    public boolean changeDateOfDeparture(@NotNull Booking booking,
                                         @NotNull Date newDate) throws SQLException {
        String query =  "update Бронирование set Выезд = " + java.sql.Date.valueOf(String.valueOf(newDate)) +
                        " where ID = " + booking.getId();
        int res = bookingRepository.updateBooking(statement, query);
        if (res != 1) {
            throw new SQLException("Не удалось изменить дату выезда у данного бронирования");
        }
        return true;
    }

}
