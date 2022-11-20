package org.stolbovik.database.hotel.models;

import lombok.Data;

import java.util.Date;

@Data
public class Employee {

    private int id;
    private String FIO;
    private long passport;
    private Date birthday;
    private Integer idOFPost;
    private boolean statusOfEmployment;
    private Integer idOfEquipment;
    private int prize;

    public boolean getStatusOfEmployment() {
        return statusOfEmployment;
    }
}
