package org.stolbovik.database.hotel.GUI.Listeners.AdminListeners;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.controller.BookingController;
import org.stolbovik.database.hotel.utils.HelpFunction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class CheckInGuestLestener implements ActionListener {

    private final JPasswordField passportField;
    private final JTextField FIOField;
    private final JTextField dataEndField;
    private final JTextField priceField;
    private final JLabel info;
    private final BookingController bookingController;

    public CheckInGuestLestener(@NotNull JPasswordField passportField,
                                @NotNull JTextField fioField,
                                @NotNull JTextField dataEndField,
                                @NotNull JTextField priceField,
                                @NotNull JLabel info) {
        this.passportField = passportField;
        this.FIOField = fioField;
        this.dataEndField = dataEndField;
        this.priceField = priceField;
        this.info = info;
        this.bookingController = new BookingController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag = true;
        int backPr = 0;
        String key = "";
        try {
            String start = HelpFunction.dateToSqlDate(new Date(new Date().getTime()/* + 24*60*60*1000*/));
            System.out.println(start);
            if (!HelpFunction.checkDataString(dataEndField.getText())) {
                throw new IllegalArgumentException("Неверный формат даты конца");
            }
            Date startDate = new Date(Integer.parseInt(start.substring(0,4)) - 1900,
                    Integer.parseInt(start.substring(5,7)) - 1,
                    Integer.parseInt(start.substring(8,10)));
            Date endDate = new Date(Integer.parseInt(dataEndField.getText().substring(0,4)) - 1900,
                    Integer.parseInt(dataEndField.getText().substring(5,7)) - 1,
                    Integer.parseInt(dataEndField.getText().substring(8,10)));
            int count = 0;
            for(char c: FIOField.getText().toCharArray()) {
                if (c == ' ') {
                    count++;
                }
            }
            if (count != 2 || FIOField.getText().length() < 5) {
                throw new IllegalArgumentException("Неверный формат имени");
            }
            String firstName = FIOField.getText().substring(0, FIOField.getText().indexOf(' '));
            String temp = FIOField.getText().substring(FIOField.getText().indexOf(' ') + 1);
            String lastName = temp.substring(0, temp.indexOf(' '));
            String fathername = temp.substring(temp.indexOf(' ') + 1);
            bookingController.bookRoom(passportField.getText(), firstName, lastName, fathername, startDate, endDate);
            int pr =  bookingController.getPricebyBooking(passportField.getText());
            if (Integer.parseInt(priceField.getText()) < pr) {
                bookingController.deleteBookingBiId(bookingController.getAllBooking().get(bookingController.getAllBooking().size() - 1).getId());
                throw new IllegalArgumentException("Недостаточно средств. Нужно: " + pr);
            } else if (Integer.parseInt(priceField.getText()) > pr) {
                backPr = Integer.parseInt(priceField.getText()) - pr;
            }
            key = bookingController.checkIntoTheBookedRoom(passportField.getText());
        } catch (Exception ex) {
            flag = false;
            info.setText(ex.getMessage());
        }
        if (flag) {
            if (backPr == 0) {
                info.setText("Ключ: " + key);
            } else {
                info.setText("Ключ: " + key + "и сдача: " + backPr);
            }
        }
    }

}
