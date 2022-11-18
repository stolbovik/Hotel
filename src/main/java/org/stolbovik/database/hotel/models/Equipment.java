package org.stolbovik.database.hotel.models;

import lombok.Data;

@Data
public class Equipment {

    private int id;
    private String name;
    private boolean statusOfUsage;

}
