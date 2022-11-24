package org.stolbovik.database.hotel.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public abstract class HelpFunction {

    public static void checkDatesWithNow(@NotNull Date start,
                                         @NotNull Date end) throws IllegalArgumentException {
        if (start.compareTo(end) >= 0) {
            throw new IllegalArgumentException("Начальная дата должна быть раньше конечной");
        }
        if (start.compareTo(new Date()) <= 0) {
            throw new IllegalArgumentException("Начальная дата должна быть в будущем");
        }
    }

    public static void checkDates(@NotNull Date start,
                                         @NotNull Date end) throws IllegalArgumentException {
        if (start.compareTo(end) >= 0) {
            throw new IllegalArgumentException("Начальная дата должна быть раньше конечной");
        }
    }

    public static void checkPassport(@NotNull String passport) throws IllegalArgumentException {
        if (passport.length() != 10) {
            throw new IllegalArgumentException("Неккоректный паспорт");
        }
        for (int i = 0; i < 10; i++) {
            char number = passport.charAt(i);
            if (!Character.isDigit(number)) {
                throw new IllegalArgumentException("Неккоректный паспорт");
            }
        }
    }

    public static void checkName(@NotNull String name) throws IllegalArgumentException {
        for (int i = 0; i < name.length(); i++) {
            char let = name.charAt(i);
            if (!Character.isLetter(let) ||
                    i == 0 && Character.isLowerCase(let) ||
                    i != 0 && !Character.isLowerCase(let)) {
                throw new IllegalArgumentException("Неккоректное введённое имя");
            }
        }
    }

    public static void checkDate(@NotNull Date date) throws IllegalArgumentException {
        if (date.compareTo(new Date()) < 0) {
            throw new IllegalArgumentException("Нельзя заказать услугу в прошлое");
        }
    }

    public static String dateToSqlDate(@NotNull Date date) {
        int year = date.getYear() + 1900;
        int month = date.getMonth() + 1;
        int day = date.getDate();
        return year + "-" + month + "-" + day;
    }

    public static void checkAdulthood(@NotNull Date date) {
        date.setYear(date.getYear() + 18);
        if (date.compareTo(new Date()) > 0) {
            throw new IllegalArgumentException("Мы нанимаем только совершеннолетних");
        }
    }

    public static int getDayBetweenDate(@NotNull Date start,
                                        @NotNull Date end) {
        return (int) Math.ceil((float) (end.getTime() - start.getTime()) / (24 * 60 * 60 * 1000)) + 1;
    }

    public static boolean checkDataString(@NotNull String data) {
        if (data.length() != 10 ||
                data.charAt(4) != '-' ||
                data.charAt(7) != '-') {
            return false;
        }
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) != '-') {
                if (!Character.isDigit(data.charAt(i)))
                    return false;
            }
        }
        return true;
    }
}
