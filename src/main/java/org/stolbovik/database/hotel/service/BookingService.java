package org.stolbovik.database.hotel.service;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Booking;
import org.stolbovik.database.hotel.models.Client;
import org.stolbovik.database.hotel.models.Room;
import org.stolbovik.database.hotel.repository.BookingRepository;
import org.stolbovik.database.hotel.repository.ClientRepository;
import org.stolbovik.database.hotel.repository.RoomsRepository;
import org.stolbovik.database.hotel.utils.Constatns;
import org.stolbovik.database.hotel.utils.HelpFunction;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class BookingService {

    private final BookingRepository bookingRepository;
    private final ClientRepository clientRepository;
    private final RoomsRepository roomsRepository;
    private final Statement statement;

    public BookingService() {
        this.bookingRepository = new BookingRepository();
        this.clientRepository = new ClientRepository();
        this.roomsRepository = new RoomsRepository();
        this.statement = Constatns.STATEMENT;
    }

    public List<Booking> getBookings() throws SQLException {
        String query = "select * from Бронирование";
        Optional<List<Booking>> list = bookingRepository.readBookings(statement, query);
        if (list.isPresent()) {
            return list.get();
        }
        throw new SQLException("Таблица \"Бронирование\" пуста\n");
    }

    public void addBooking(@NotNull Date start,
                           @NotNull Date end,
                           @NotNull Client client,
                           @NotNull Room room,
                           int paymentState) throws SQLException {

        String query =  "insert into Бронирование (Прибывание, Выезд, [Статус заселения]," +
                " [Статус оплаты], [ID Клиента], [Номер комнаты]) values ('" +
                        HelpFunction.dateToSqlDate(start) + "', '" +
                        HelpFunction.dateToSqlDate(end) + "', 0, " + paymentState +
                        ", " + client.getId() + ", " + room.getId() + ")";
        int res = bookingRepository.updateBooking(statement, query);
        if (res != 1) {
            throw new SQLException("Не удалось добавить бронь");
        }
    }

    public Booking getTodayStayBookingByPassport(@NotNull String passport) throws SQLException, IllegalArgumentException {
        String query = "select * from Клиенты where Паспорт = " + passport;
        Optional<List<Client>> list = clientRepository.readClients(statement, query);
        if (list.isEmpty()) {
            throw new SQLException("Данного клиента нет в базе данных");
        }
        Client client = list.get().get(0);
        query = "select * from Бронирование where [ID Клиента] = " + client.getId() + " AND " +
                "Прибывание = '" + HelpFunction.dateToSqlDate(new Date()) + "'";
        Optional<List<Booking>> list2 = bookingRepository.readBookings(statement, query);
        if (list2.isEmpty()) {
            throw new IllegalArgumentException("Брони на заезд на сегодня на данного клиента нет");
        }
        return list2.get().get(0);
    }

    public Booking getTodayBookingByPassport(@NotNull String passport) throws SQLException, IllegalArgumentException {
        String query = "select * from Клиенты where Паспорт = " + passport;
        Optional<List<Client>> list = clientRepository.readClients(statement, query);
        if (list.isEmpty()) {
            throw new SQLException("Данного клиента нет в базе данных");
        }
        Client client = list.get().get(0);
        query = "select * from Бронирование where [ID Клиента] = " + client.getId() + " AND " +
                "Прибывание <= '" + HelpFunction.dateToSqlDate(new Date()) +
                "' AND Выезд >= '" + HelpFunction.dateToSqlDate(new Date()) + "'";
        Optional<List<Booking>> list2 = bookingRepository.readBookings(statement, query);
        if (list2.isEmpty()) {
            throw new IllegalArgumentException("Брони на сегодня на данного клиента нет");
        }
        return list2.get().get(0);
    }

    public Booking getTodayBookingByIdOfRoom(int id) throws SQLException, IllegalArgumentException {
        String query = "select * from Бронирование where [Номер комнаты] = " + id + " AND " +
                "Прибывание <= '" + HelpFunction.dateToSqlDate(new Date()) +
                "' AND Выезд >= '" + HelpFunction.dateToSqlDate(new Date()) + "'";
        Optional<List<Booking>> list2 = bookingRepository.readBookings(statement, query);
        if (list2.isEmpty()) {
            throw new IllegalArgumentException("Брони на сегодня на данного клиента нет");
        }
        return list2.get().get(0);
    }

    public Booking getBookingById(int id) throws SQLException, IllegalArgumentException {
        String query = "select * from Бронирование where ID = " + id;
        Optional<List<Booking>> list2 = bookingRepository.readBookings(statement, query);
        if (list2.isEmpty()) {
            throw new IllegalArgumentException("Брони с данным номером нет");
        }
        return list2.get().get(0);
    }

    public void setPaymentStatus(@NotNull Booking booking, boolean status) throws SQLException {
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
    }

    public void setSettlementStatus(@NotNull Booking booking, boolean status) throws SQLException {
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
    }

    public void setDateOfDeparture(@NotNull Booking booking, @NotNull Date newDate) throws SQLException {
        String query = "update Бронирование set Выезд = '" + HelpFunction.dateToSqlDate(newDate) + "' "
                + " where ID = " + booking.getId();
        int res = bookingRepository.updateBooking(statement, query);
        if (res != 1) {
            throw new SQLException("Не удалось изменить дату выезда у данного бронирования");
        }
    }

    public boolean changeDateOfDeparture(@NotNull Booking booking,
                                         @NotNull Date newDate) throws SQLException {
        String query =  "update Бронирование set Выезд = '" + HelpFunction.dateToSqlDate(newDate) +
                        "' where ID = " + booking.getId();
        int res = bookingRepository.updateBooking(statement, query);
        if (res != 1) {
            throw new SQLException("Не удалось изменить дату выезда у данного бронирования");
        }
        return true;
    }

    public int getSumIncome(@NotNull Date start,
                            @NotNull Date end) throws SQLException {
        List<Booking> list = getBookings();
        int income = 0;
        for(Booking booking: list) {
            if ((start.compareTo(booking.getDateOfStay()) < 0) && (end.compareTo(booking.getDateOfStay()) < 0) ||
                (start.compareTo(booking.getDateOfDeparture()) > 0) && (end.compareTo(booking.getDateOfDeparture()) > 0)) {
                continue;
            }
            String query = "select * from Комнаты where ID = " + booking.getIdOfRoom();
            int price = roomsRepository.readRooms(statement, query).get().get(0).getPriceInDay();
            long relativelyDay = 0;
            if ((start.compareTo(booking.getDateOfStay()) <= 0) && (end.compareTo(booking.getDateOfDeparture()) <= 0)) {
                relativelyDay = HelpFunction.getDayBetweenDate(booking.getDateOfStay(),end);
            } else if ((start.compareTo(booking.getDateOfStay()) <= 0) && (end.compareTo(booking.getDateOfDeparture()) >= 0)) {
                relativelyDay = HelpFunction.getDayBetweenDate(booking.getDateOfStay(),booking.getDateOfDeparture());
            } else if ((start.compareTo(booking.getDateOfStay()) >= 0) && (end.compareTo(booking.getDateOfDeparture()) <= 0)) {
                relativelyDay = HelpFunction.getDayBetweenDate(start, end);
            } else if ((start.compareTo(booking.getDateOfStay()) >= 0) && (end.compareTo(booking.getDateOfDeparture()) >= 0)) {
                relativelyDay = HelpFunction.getDayBetweenDate(start, booking.getDateOfDeparture());
            }
            income += price * relativelyDay;
        }
        return income;
    }

}
