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
import java.sql.Statement;
import java.util.Date;
import java.util.List;

public class BookingController {

    private final BookingService bookingService;
    private final RoomService roomService;
    private final ClientService clientService;
    private final RequestForCleaningService requestForCleaningService;

    public BookingController(@NotNull Statement statement) {
        this.bookingService = new BookingService(statement);
        this.roomService = new RoomService(statement);
        this.clientService = new ClientService(statement);
        this.requestForCleaningService = new RequestForCleaningService(statement);
    }

    public int bookRoom(@NotNull String passport, @NotNull String firstName, @NotNull String lastName,
                        @NotNull String fatherName, @NotNull Date start, @NotNull Date end)
                        throws SQLException, IllegalArgumentException {
        HelpFunction.checkDates(start, end);
        HelpFunction.checkName(firstName);
        HelpFunction.checkName(lastName);
        HelpFunction.checkName(fatherName);
        HelpFunction.checkPassport(passport);
        Room room = roomService.getFreeRooms(start, end).get(0);
        if (!clientService.checkClientByPassport(passport)) {
            clientService.addClient(firstName, lastName, fatherName, passport);
        }
        Client client = clientService.getClientByPassport(passport);
        bookingService.addBooking(start, end, client, room, 1);
        return room.getPriceInDay() * HelpFunction.getDayBetweenDate(start, end);
    }

    public String checkIntoTheBookedRoom(@NotNull String passport) throws SQLException, IllegalArgumentException {
        Booking booking = bookingService.getTodayStayBookingByPassport(passport);
        bookingService.setSettlementStatus(booking, true);
        Room room = roomService.getRoomByID(booking.getIdOfRoom());
        return room.getKey();
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

    public int extendBooking(int numRoom, @NotNull Date newEnd)
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
            throw new IllegalArgumentException("Ваша комната не свободна для продления");
        }
        bookingService.setDateOfDeparture(booking, newEnd);
        return room.getPriceInDay() * HelpFunction.getDayBetweenDate(booking.getDateOfDeparture(), newEnd);
    }

    public int calculateProfitFromBookings(@NotNull Date start,
                                           @NotNull Date end) throws SQLException {
        HelpFunction.checkDates(start, end);
        return bookingService.getSumIncome(start, end);
    }

}
