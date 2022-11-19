package org.stolbovik.database.hotel.models;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.sql.Date;

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
