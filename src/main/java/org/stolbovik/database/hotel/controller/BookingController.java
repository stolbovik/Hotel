package org.stolbovik.database.hotel.controller;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Booking;
import org.stolbovik.database.hotel.models.Client;
import org.stolbovik.database.hotel.models.Room;
import org.stolbovik.database.hotel.service.BookingService;
import org.stolbovik.database.hotel.service.ClientService;
import org.stolbovik.database.hotel.service.RequestForCleaningService;
import org.stolbovik.database.hotel.service.RoomService;
import org.stolbovik.database.hotel.utils.Constatns;
import org.stolbovik.database.hotel.utils.HelpFunction;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class BookingController {

    private final BookingService bookingService;
    private final RoomService roomService;
    private final ClientService clientService;
    private final RequestForCleaningService requestForCleaningService;

    public BookingController() {
        this.bookingService = new BookingService();
        this.roomService = new RoomService();
        this.clientService = new ClientService();
        this.requestForCleaningService = new RequestForCleaningService();
    }

    public void bookRoom(@NotNull String passport, @NotNull String firstName, @NotNull String lastName,
                        @NotNull String fatherName, @NotNull Date start, @NotNull Date end)
                        throws SQLException, IllegalArgumentException {
        HelpFunction.checkDatesWithNowAndNowDay(start, end);
        HelpFunction.checkName(firstName);
        HelpFunction.checkName(lastName);
        HelpFunction.checkName(fatherName);
        HelpFunction.checkPassport(passport);
        List<Booking> list = bookingService.getBookings();
        Room room = roomService.getFreeRooms(start, end).get(0);
        if (!clientService.checkClientByPassport(passport)) {
            clientService.addClient(firstName, lastName, fatherName, passport);
        }
        Client client = clientService.getClientByPassport(passport);
        for (Booking booking : list) {
            if (booking.getIdOfClient() == client.getId() && (
                start.compareTo(booking.getDateOfStay()) >= 0 && start.compareTo(booking.getDateOfDeparture()) <= 0 ||
                        end.compareTo(booking.getDateOfStay()) >= 0 && end.compareTo(booking.getDateOfDeparture()) <= 0 ||
                        start.compareTo(booking.getDateOfStay()) <= 0 && end.compareTo(booking.getDateOfDeparture()) >= 0)) {
                throw new IllegalArgumentException("Параллельные брони запрещены");
            }
        }
        bookingService.addBooking(start, end, client, room, 1);;
    }

    public List<Booking> getAllBooking() throws SQLException {
        return bookingService.getBookings();
    }
    public String checkIntoTheBookedRoom(@NotNull String passport) throws SQLException, IllegalArgumentException {
        HelpFunction.checkPassport(passport);
        Booking booking = bookingService.getTodayStayBookingByPassport(passport);
        bookingService.setSettlementStatus(booking, true);
        Room room = roomService.getRoomByID(booking.getIdOfRoom());
        return room.getKey();
    }

    public int getPricebyBooking(@NotNull String passport) throws SQLException, IllegalArgumentException {
        HelpFunction.checkPassport(passport);
        Booking booking = bookingService.getTodayStayBookingByPassport(passport);
        Room room = roomService.getRoomByID(booking.getIdOfRoom());
        return room.getPriceInDay() * HelpFunction.getDayBetweenDate(booking.getDateOfStay(), booking.getDateOfDeparture());
    }

    public Booking getBookingByNumRoom(@NotNull String numRoom) throws SQLException, IllegalArgumentException {
        return bookingService.getTodayBookingByIdOfRoom(roomService.getRoomByNumber(Integer.parseInt(numRoom)).getId());
    }

    public void moveOutFromRoom(@NotNull String key) throws SQLException, IllegalArgumentException {
        Room room = roomService.getRoomByKey(key);
        Booking booking = bookingService.getTodayBookingByIdOfRoom(room.getId());
        bookingService.setSettlementStatus(booking, false);
        roomService.setCleaningStatus(room, false);
        requestForCleaningService.addRequest(booking);
        if (booking.getDateOfDeparture().compareTo(new Date()) > 0) {
            bookingService.setDateOfDeparture(booking, new Date());
        }
    }

    public void extendBooking(int numRoom, @NotNull Date newEnd)
            throws SQLException, IllegalArgumentException {
        Room room = roomService.getRoomByNumber(numRoom);
        HelpFunction.checkDate(newEnd);
        Booking booking = bookingService.getTodayBookingByIdOfRoom(room.getId());
        if (booking.getDateOfDeparture().compareTo(newEnd) > 0) {
            throw new IllegalArgumentException("Новая дата выезда должна быть позже предыдущей");
        }
        Date nextAfterLastDate = new Date(booking.getDateOfDeparture().getTime() + Constatns.MILLISECOND_IN_DAY);
        List<Room> listOfRoom = roomService.getFreeRooms(nextAfterLastDate, newEnd);
        boolean flag = false;
        for (Room room1: listOfRoom) {
            if (room1.getNumOfRoom() == room.getNumOfRoom()) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            throw new IllegalArgumentException("Комната занята для продления");
        }
        bookingService.setDateOfDeparture(booking, newEnd);
    }

    public int calculateProfitFromBookings(@NotNull Date start,
                                           @NotNull Date end) throws SQLException {
        HelpFunction.checkDates(start, end);
        return bookingService.getSumIncome(start, end);
    }

    public void deleteBookingBiId(int id) throws SQLException {
        bookingService.deleteBookingBiId(id);
    }
}
