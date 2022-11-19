package org.stolbovik.database.hotel.models;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class Client {

    private int id;
    private String FIO;
    private long passport;

}
