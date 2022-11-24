package org.stolbovik.database.hotel.controller;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Room;
import org.stolbovik.database.hotel.service.RoomService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomController {

    private final RoomService roomService;

    public RoomController() {
        this.roomService = new RoomService();
    }

    public int getPriceRoomByNum(int num) throws SQLException {
        return roomService.getRoomByNumber(num).getPriceInDay();
    }

    public Room getFirstFreeRoom(@NotNull Date start,
                                 @NotNull Date end) throws SQLException {
        return roomService.getFreeRooms(start, end).get(0);
    }

}
