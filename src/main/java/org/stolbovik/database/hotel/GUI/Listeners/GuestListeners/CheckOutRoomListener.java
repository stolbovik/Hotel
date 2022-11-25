package org.stolbovik.database.hotel.GUI.Listeners.GuestListeners;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.controller.BookingController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckOutRoomListener implements ActionListener {

    private final JLabel info;
    private final JTextField keyField;
    private final BookingController bookingController;


    public CheckOutRoomListener(@NotNull JLabel info,
                                @NotNull JTextField keyField) {
        this.info = info;
        this.keyField = keyField;
        this.bookingController = new BookingController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag = true;
        try {
            bookingController.moveOutFromRoom(keyField.getText());
        } catch (Exception ex) {
            flag = false;
            info.setText(ex.getMessage());
        }
        if (flag) {
            info.setText("Вы высылены");
        }
    }

}
