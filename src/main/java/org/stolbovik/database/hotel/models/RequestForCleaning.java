package org.stolbovik.database.hotel.models;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.sql.Date;

@Data
public class RequestForCleaning {

    private int id;
    private int idOfBooking;
    private int idOfStatusRequest;
    private Date dateOfRequest;
    private Integer idOfEmployee;

}
