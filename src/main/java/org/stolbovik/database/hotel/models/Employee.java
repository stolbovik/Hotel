package org.stolbovik.database.hotel.models;

import lombok.Data;

import java.sql.Date;

@Data
public class Employee {

    private int id;
    private String FIO;
    private long passport;
    private Date birthday;
    private int idOFPost;
    private boolean statusOfEmployment;
    private int idOfEquipment;
    private int prize;

    public boolean getStatusOfEmployment() {
        return statusOfEmployment;
    }
}
