package org.stolbovik.database.hotel.models;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class User {

    private int id;
    private String login;
    private String password;
    private String role;

}
