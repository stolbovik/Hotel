package org.stolbovik.database.hotel.GUI.Listeners.FinanceListeners;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.controller.BookingController;
import org.stolbovik.database.hotel.utils.HelpFunction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class CalculateProfitFromBookingListener implements ActionListener {

    private final JLabel info;
    private final JTextField dataStartField;
    private final JTextField dataEndField;
    private final BookingController bookingController;

    public CalculateProfitFromBookingListener(@NotNull JLabel info,
                                    @NotNull JTextField dataStartField,
                                    @NotNull JTextField dataEndField) {
        this.info = info;
        this.dataStartField = dataStartField;
        this.dataEndField = dataEndField;
        this.bookingController = new BookingController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag = true;
        int sum = 0;
        try {
            if (!HelpFunction.checkDataString(dataEndField.getText()) ||
                    !HelpFunction.checkDataString(dataStartField.getText())) {
                throw new IllegalArgumentException("Неверный формат дат");
            }
            Date start = new Date(Integer.parseInt(dataStartField.getText().substring(0,4)) - 1900,
                    Integer.parseInt(dataStartField.getText().substring(5,7)) - 1,
                    Integer.parseInt(dataStartField.getText().substring(8,10)));
            Date end = new Date(Integer.parseInt(dataEndField.getText().substring(0,4)) - 1900,
                    Integer.parseInt(dataEndField.getText().substring(5,7)) - 1,
                    Integer.parseInt(dataEndField.getText().substring(8,10)));
            sum = bookingController.calculateProfitFromBookings(start, end);
        } catch (Exception ex) {
            flag = false;
            info.setText(ex.getMessage());
        }
        if (flag) {
            info.setText("Сумма: " + sum);
        }
    }

}
