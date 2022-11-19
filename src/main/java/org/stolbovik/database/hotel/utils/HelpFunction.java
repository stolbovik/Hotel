package org.stolbovik.database.hotel.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public abstract class HelpFunction {

    public static boolean checkDates(@NotNull Date start,
                                     @NotNull Date end) {
        if(start.compareTo(end) >= 0) {
            throw new IllegalArgumentException("Дата заезда должна быть раньше даты выезда");
        }
        if (start.compareTo(new Date()) <= 0) {
            throw new IllegalArgumentException("Дата заезда должна быть указана в будущем");
        }
        return true;
    }

    public static boolean checkPassport(@NotNull String passport) {
        if (passport.length() != 10) {
            throw new IllegalArgumentException("Неккоректные паспортные данные");
        }
        for (int i = 0; i < 10; i++) {
            char number = passport.charAt(i);
            if (!Character.isDigit(number)) {
                throw new IllegalArgumentException("Неккоректные паспортные данные");
            }
        }
        return true;
    }

    public static boolean checkName(@NotNull String name) {
        for (int i = 0; i < 10; i++) {
            char let = name.charAt(i);
            if (!Character.isLetter(let) ||
                i == 0 && Character.isLowerCase(let) ||
                i != 0 && !Character.isLowerCase(let)) {
                throw new IllegalArgumentException("Неккоректное введённое имя");
            }
        }
        return true;
    }

    public static boolean checkDate(@NotNull Date date) {
        if (date.compareTo(new Date()) < 0) {
            throw new IllegalArgumentException("Неккоректная дата");
        }
        return true;
    }

    public static String dateToSqlDate(@NotNull Date date) {
        int year = date.getYear() + 1900;
        int month = date.getMonth() + 1;
        int day = date.getDate();
        return year + "-" + month + "-" + day;
    }

    public static boolean checkAdulthood(@NotNull Date date) {
        date.setYear(date.getYear() + 18);
        return !(date.compareTo(new Date()) > 0);
    }

    public static long getDayBetweenDate(@NotNull Date start,
                                         @NotNull Date end) {
        return (end.getTime() - start.getTime())/(24 * 60 * 60 * 1000);
    }
}
