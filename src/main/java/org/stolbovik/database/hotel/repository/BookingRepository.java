package org.stolbovik.database.hotel.repository;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Booking;
import org.stolbovik.database.hotel.models.EntryInBook;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingRepository {

    public Optional<List<Booking>> readBookings( @NotNull Statement statement,
                                                 @NotNull String query) throws SQLException {
        ResultSet resultSet = statement.executeQuery(query);
        if (!resultSet.next()) {
            return Optional.empty();
        }
        List<Booking> bookings = new ArrayList<>();
        do {
            Booking booking = new Booking();
            booking.setId(resultSet.getInt("ID"));
            booking.setDateOfStay(resultSet.getDate("Прибывание"));
            booking.setDateOfDeparture(resultSet.getDate("Выезд"));
            booking.setStatusOfSettlement(resultSet.getBoolean("Статус заселения"));
            booking.setStatusOfPayment(resultSet.getBoolean("Статус оплаты"));
            booking.setIdOfClient(resultSet.getInt("ID клиента"));
            booking.setIdOfRoom(resultSet.getInt("Номер комнаты"));
            bookings.add(booking);
        } while (resultSet.next());
        return Optional.of(bookings);
    }

    public int updateBooking(@NotNull Statement statement,
                                    @NotNull String query) throws SQLException {
        return statement.executeUpdate(query);
    }

}
