package org.stolbovik.database.hotel.models;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class StatusOfCleaningRequest {

    private int id;
    private String status;

}
