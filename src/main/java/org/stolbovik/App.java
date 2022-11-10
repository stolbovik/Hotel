package org.stolbovik;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import java.sql.*;

public class App
{
    private static final String URL = "jdbc:sqlserver://26.25.222.222\\MSSQLSERVER:1433;database=Hostel;trustServerCertificate=true";
    private static final String LOGIN = "admin";
    private static final String PASSWORD = "Lovemother79";

    public static void main( String[] args ){
        System.setProperty("java.net.preferIPv3Addresses", "true");
        try {
            Driver driver = new SQLServerDriver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try(Connection connection = DriverManager.getConnection(URL,LOGIN,PASSWORD);
            Statement statement = connection.createStatement()) {
/*            statement.execute("insert into Клиенты(ФИО, Паспорт) values ('С С С', 1111111111)");*/
/*            statement.execute("delete from Клиенты where id = 32");*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
