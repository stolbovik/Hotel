package org.stolbovik.database.hotel.models;

import lombok.Data;

@Data
public class ServiceWithEmployee {

    private int id;
    private Integer idService;
    private Integer idEmployee;

}
