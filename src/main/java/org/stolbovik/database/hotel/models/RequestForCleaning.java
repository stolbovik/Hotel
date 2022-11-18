package org.stolbovik.database.hotel.models;

import lombok.Data;

import java.sql.Date;

@Data
public class RequestForCleaning {

    private int id;
    private int idOfBooking;
    private int idOfStatusRequest;
    private Date dateOfRequest;
    private int idOfEmployee;

}
