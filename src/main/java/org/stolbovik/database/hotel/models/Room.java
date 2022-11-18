package org.stolbovik.database.hotel.models;

import lombok.Data;

@Data
public class Room {

    private int id;
    private int numOfRoom;
    private String key;
    private boolean cleaningOfStatus;
    private int priceInDay;

}
