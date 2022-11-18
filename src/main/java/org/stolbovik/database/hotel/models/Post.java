package org.stolbovik.database.hotel.models;

import lombok.Data;

@Data
public class Post {

    private int id;
    private String nameOfPost;
    private int countOfRequiredEmployees;
    private int salary;

}
