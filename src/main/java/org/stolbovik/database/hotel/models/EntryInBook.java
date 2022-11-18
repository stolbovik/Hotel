package org.stolbovik.database.hotel.models;

import lombok.Data;

@Data
public class EntryInBook {

    private int id;
    private int idOfClient;
    private String text;
    private String answer;

}
