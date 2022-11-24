package org.stolbovik.database.hotel.GUI.Listeners.GuestListeners;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.controller.BookingController;
import org.stolbovik.database.hotel.controller.OrderedServiceController;
import org.stolbovik.database.hotel.controller.PaidServiceController;
import org.stolbovik.database.hotel.controller.RoomController;
import org.stolbovik.database.hotel.models.Room;
import org.stolbovik.database.hotel.utils.HelpFunction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class BookRoomListener implements ActionListener {

    private final JLabel info;
    private final JPasswordField passportField;
    private final JTextField FIOField;
    private final JTextField dataStartField;
    private final JTextField dataEndField;
    private final BookingController bookingController;


    public BookRoomListener(@NotNull JLabel info,
                                @NotNull JTextField FIOField,
                                @NotNull JPasswordField passportField,
                                @NotNull JTextField dataStartField,
                                @NotNull JTextField dataEndField) {
        this.info = info;
        this.FIOField = FIOField;
        this.passportField = passportField;
        this.dataStartField = dataStartField;
        this.dataEndField = dataEndField;
        this.bookingController = new BookingController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag = true;
        int backPr = 0;
        try {
            if (!HelpFunction.checkDataString(dataStartField.getText())) {
                throw new IllegalArgumentException("Неверный формат даты начала");
            }
            if (!HelpFunction.checkDataString(dataEndField.getText())) {
                throw new IllegalArgumentException("Неверный формат даты конца");
            }
            Date startDate = new Date(Integer.parseInt(dataStartField.getText().substring(0,4)) - 1900,
                    Integer.parseInt(dataStartField.getText().substring(5,7)) - 1,
                    Integer.parseInt(dataStartField.getText().substring(8,10)));
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
        } catch (Exception ex) {
            flag = false;
            info.setText(ex.getMessage());
        }
        if (flag) {
            info.setText("Комната забронирована");
        }
    }

}
