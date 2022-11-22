package org.stolbovik.database.hotel.utils;

import org.jetbrains.annotations.NotNull;

import java.sql.Statement;

public final class Constatns {

    private Constatns(){}

    public static final String URL = "jdbc:sqlserver://26.25.222.222\\MSSQLSERVER:1433;database=Hostel;trustServerCertificate=true";
    public static final String LOGIN = "admin";
    public static final String PASSWORD = "Lovemother79";
    public static Statement statement;

    public static void setStatement(@NotNull Statement statement1) {
        statement = statement1;
    }
    public static final int MILLISECOND_IN_DAY = 86400000;
    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 400;
    public static boolean applicationIsWork = true;
}
