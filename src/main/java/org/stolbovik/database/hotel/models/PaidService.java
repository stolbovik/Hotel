package org.stolbovik.database.hotel.models;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class PaidService {

    private int id;
    private String name;
    private int price;
    private float timeForService;

}
