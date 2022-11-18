package org.stolbovik.database.hotel.models;

import lombok.Data;

@Data
public class PaidService {

    private int id;
    private String name;
    private int price;
    private float timeForService;

}
