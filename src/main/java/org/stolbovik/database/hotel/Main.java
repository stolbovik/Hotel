package org.stolbovik.database.hotel;

import org.stolbovik.database.hotel.GUI.Application;
import org.stolbovik.database.hotel.utils.Constatns;
import org.stolbovik.database.hotel.utils.DBConnector;

import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main( String[] args ) {
        DBConnector dbConnector = new DBConnector(Constatns.URL, Constatns.LOGIN, Constatns.PASSWORD);
        try(Statement statement = dbConnector.getConnection().createStatement()) {
            Constatns.setSTATEMENT(statement);
            new Application().run();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnector.close();
        Constatns.applicationIsWork = true;
    }
}
