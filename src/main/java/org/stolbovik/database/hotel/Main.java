package org.stolbovik.database.hotel;

import org.stolbovik.database.hotel.models.Booking;
import org.stolbovik.database.hotel.models.Role;
import org.stolbovik.database.hotel.models.Room;
import org.stolbovik.database.hotel.repository.BookingRepository;
import org.stolbovik.database.hotel.repository.RoomsRepository;
import org.stolbovik.database.hotel.utils.Constatns;
import org.stolbovik.database.hotel.utils.DBConnector;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main( String[] args ){
        String query1 = "select * from Роли";
        String query2 = "select * from Комнаты where [Статус чистоты] = 1";
        String query3 = "insert into [Книга жалоб и предложений] values (1,'Пиздец','Да пиздец')";
        String query4 = "select * from Бронирование";
/*        System.setProperty("java.net.preferIPv3Addresses", "true");*/
        DBConnector dbConnector = new DBConnector(Constatns.URL, Constatns.LOGIN, Constatns.PASSWORD);
        try(Statement statement = dbConnector.getConnection().createStatement()) {

            ResultSet resultSet = statement.executeQuery(query1);

            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt("ID"));
                role.setRole(resultSet.getString("Роль"));
                System.out.println(role);
            }

            RoomsRepository roomsRepository = new RoomsRepository();
            Optional<List<Room>> rooms = roomsRepository.readRooms(statement, query2);
            rooms.ifPresent(System.out::println);

            BookingRepository bookingRepository = new BookingRepository();
            Optional<List<Booking>> bookings = bookingRepository.readBookings(statement, query4);
            bookings.ifPresent(System.out::println);
/*            int a = ComplaintBookRepository.updateComplaintBook(statement, query3);
            System.out.println(a);*/

        } catch (SQLException e) {
            e.printStackTrace();
        }

        dbConnector.close();
    }
}
