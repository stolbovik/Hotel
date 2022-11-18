package org.stolbovik.database.hotel.utils;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import lombok.Data;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

@Data
public class DBConnector {

    private final String URL;
    private final String LOGIN;
    private final String PASSWORD;
    private Connection connection;

    public DBConnector(String URL, String LOGIN, String PASSWORD) {
        this.URL = URL;
        this.LOGIN = LOGIN;
        this.PASSWORD = PASSWORD;
        try {
            Driver driver = new SQLServerDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL,LOGIN,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
