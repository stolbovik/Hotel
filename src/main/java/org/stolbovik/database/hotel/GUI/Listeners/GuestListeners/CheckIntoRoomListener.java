package org.stolbovik.database.hotel.GUI.Listeners.GuestListeners;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.controller.BookingController;
import org.stolbovik.database.hotel.controller.RoomController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckIntoRoomListener implements ActionListener {

    private final JLabel info;
    private final JPasswordField passportField;
    private final JTextField priceField;
    private final BookingController bookingController;
    private final RoomController roomController;


    public CheckIntoRoomListener(@NotNull JLabel info,
                                 @NotNull JTextField priceField,
                                 @NotNull JPasswordField passportField) {
        this.info = info;
        this.priceField = priceField;
        this.passportField = passportField;
        this.bookingController = new BookingController();
        this.roomController = new RoomController();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag = true;
        int backPr = 0;
        String key = "";
        try {
            int pr =  bookingController.getPricebyBooking(passportField.getText());
            if (Integer.parseInt(priceField.getText()) < pr) {
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
