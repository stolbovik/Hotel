package org.stolbovik.database.hotel.models;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class EntryInBook {

    private int id;
    private int idOfClient;
    private String text;
    private String answer;

}
