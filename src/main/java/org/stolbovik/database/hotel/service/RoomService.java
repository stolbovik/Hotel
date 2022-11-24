package org.stolbovik.database.hotel.service;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Booking;
import org.stolbovik.database.hotel.models.Room;
import org.stolbovik.database.hotel.repository.BookingRepository;
import org.stolbovik.database.hotel.repository.RoomsRepository;
import org.stolbovik.database.hotel.repository.StatusOfCleaningRequestRepository;
import org.stolbovik.database.hotel.utils.Constatns;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class RoomService {

    private final RoomsRepository roomsRepository;
    private final BookingRepository bookingRepository;
    private final StatusOfCleaningRequestRepository statusOfCleaningRequestRepository;
    private final Statement statement;

    public RoomService() {
        this.roomsRepository = new RoomsRepository();
        this.bookingRepository = new BookingRepository();
        this.statusOfCleaningRequestRepository = new StatusOfCleaningRequestRepository();
        this.statement = Constatns.STATEMENT;
    }

    public List<Room> getAllRooms() throws SQLException {
        String query = "select * from Комнаты";
        Optional<List<Room>> list = roomsRepository.readRooms(statement, query);
        if (list.isPresent()) {
            return list.get();
        }
        throw new SQLException("В отеле нет комнат\n");
    }

    public Room getRoomByID(int id) throws SQLException {
        String query = "select * from Комнаты where ID = " + id;
        Optional<List<Room>> list = roomsRepository.readRooms(statement, query);
        if (list.isPresent()) {
            return list.get().get(0);
        }
        throw new SQLException("В отеле нет комнаты с таким номером\n");
    }

    public Room getRoomByKey(@NotNull String key) throws SQLException {
        String query = "select * from Комнаты where Ключ = '" + key + "'";
        Optional<List<Room>> list = roomsRepository.readRooms(statement, query);
        if (list.isPresent()) {
            return list.get().get(0);
        }
        throw new SQLException("В отеле нет комнаты с таким ключом\n");
    }

    public Room getRoomByNumber(int number) throws SQLException {
        String query = "select * from Комнаты where Номер = " + number;
        Optional<List<Room>> list = roomsRepository.readRooms(statement, query);
        if (list.isPresent()) {
            return list.get().get(0);
        }
        throw new SQLException("В отеле нет комнаты с таким номером\n");
    }

    public List<Room> getFreeRooms(@NotNull Date start,
                                             @NotNull Date end) throws SQLException, IllegalArgumentException {
        String query = "select * from Бронирование";
        List<Room> allRooms = getAllRooms();
        List<Booking> allBookings;
        Optional<List<Booking>> list = bookingRepository.readBookings(statement, query);
        if (list.isPresent()) {
            allBookings = list.get();
        } else {
            throw new SQLException("Ни на какой номер бронирования нет\n");
        }
        Iterator<Room> roomIterator = allRooms.iterator();
        Iterator<Booking> bookingIterator = allBookings.iterator();
        while (roomIterator.hasNext()) {
            while (bookingIterator.hasNext()) {
                Room room = roomIterator.next();
                Booking booking = bookingIterator.next();
                if (room.getId() == booking.getIdOfRoom() && (
                    (start.compareTo(booking.getDateOfDeparture()) <= 0) && (start.compareTo(booking.getDateOfStay()) >= 0) ||
                    (end.compareTo(booking.getDateOfDeparture()) <= 0) && (end.compareTo(booking.getDateOfStay()) >= 0) ||
                    (start.compareTo(booking.getDateOfStay()) <= 0) && (end.compareTo(booking.getDateOfDeparture()) >= 0))) {
                    roomIterator.remove();
                }
            }
        }
        if (allRooms.isEmpty()) {
            throw new IllegalArgumentException("Нет свободных номеров на эти даты");
        }
        return allRooms;
    }

    public boolean setCleaningStatus(@NotNull Room room, boolean status) throws SQLException {
        int temp;
        if (status) {
            temp = 1;
        } else {
            temp = 0;
        }
        String query = "update Комнаты set [Статус чистоты] = " + temp + " where ID = " + room.getId();
        int res = roomsRepository.updateRoom(statement, query);
        if (res != 1) {
            throw new SQLException("Не удалось изменить статус заселения у данного бронирования");
        }
        return true;
    }

}
