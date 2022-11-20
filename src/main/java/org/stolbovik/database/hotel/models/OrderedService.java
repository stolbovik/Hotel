package org.stolbovik.database.hotel.models;

import lombok.Data;

import java.util.Date;

@Data
public class OrderedService {

    private int id;
    private int serviceWithEmployee;
    private Date date;
    private int idOfRoom;

}
