package org.stolbovik.database.hotel.models;

import lombok.Data;

import java.util.Date;

@Data
public class Booking {

    private int id;
    private Date dateOfStay;
    private Date dateOfDeparture;
    private boolean statusOfSettlement;
    private boolean statusOfPayment;
    private int idOfClient;
    private int idOfRoom;

}
