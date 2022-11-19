package org.stolbovik.database.hotel.models;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class Equipment {

    private int id;
    private String name;
    private boolean statusOfUsage;

}
