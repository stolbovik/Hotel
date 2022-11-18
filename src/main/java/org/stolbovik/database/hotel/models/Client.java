package org.stolbovik.database.hotel.models;

import lombok.Data;

@Data
public class Client {

    private int id;
    private String FIO;
    private long passport;

}
