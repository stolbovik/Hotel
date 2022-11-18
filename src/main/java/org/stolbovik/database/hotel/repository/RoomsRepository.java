package org.stolbovik.database.hotel.repository;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomsRepository {

    public Optional<List<Room>> readRooms(@NotNull Statement statement,
                                          @NotNull String query) throws SQLException {
        ResultSet resultSet = statement.executeQuery(query);
        if (!resultSet.next()) {
            return Optional.empty();
        }
        List<Room> rooms = new ArrayList<>();
        do {
            Room room = new Room();
            room.setId(resultSet.getInt("id"));
            room.setNumOfRoom(resultSet.getInt("Номер"));
            room.setKey(resultSet.getString("Ключ"));
            room.setCleaningOfStatus(resultSet.getBoolean("Статус чистоты"));
            room.setPriceInDay(resultSet.getInt("Цена/сутки"));
            rooms.add(room);
        } while (resultSet.next());
        return Optional.of(rooms);
    }

    public int updateRoom(@NotNull Statement statement,
                                  @NotNull String query) throws SQLException {
        return statement.executeUpdate(query);
    }

}
