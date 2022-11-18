package org.stolbovik.database.hotel.models;

import lombok.Data;

@Data
public class ServiceWithEmployee {

    private int id;
    private int idService;
    private int idEmployee;

}
