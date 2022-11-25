package org.stolbovik.database.hotel.GUI.Listeners.GuestListeners;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.controller.BookingController;
import org.stolbovik.database.hotel.controller.RoomController;
import org.stolbovik.database.hotel.models.Booking;
import org.stolbovik.database.hotel.utils.HelpFunction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class ExtendRoomListener implements ActionListener {

    private final JLabel info;
    private final JTextField roomField;
    private final JTextField newDataEndField;
    private final JTextField priceField;
    private final BookingController bookingController;
    private final RoomController roomController;


    public ExtendRoomListener(@NotNull JLabel info,
                              @NotNull JTextField roomField,
                              @NotNull JTextField newDataEndField,
                              @NotNull JTextField priceField) {
        this.info = info;
        this.roomField = roomField;
        this.newDataEndField = newDataEndField;
        this.priceField = priceField;
        this.bookingController = new BookingController();
        this.roomController = new RoomController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag = true;
        int backPr = 0;
        try {
            String roomString = roomField.getText();
            for (Character character: roomString.toCharArray()) {
                if (!Character.isDigit(character)) {
                    throw new IllegalArgumentException("Неверный формат номера комнаты");
                }
            }
            if (!HelpFunction.checkDataString(newDataEndField.getText())) {
                throw new IllegalArgumentException("Неверный формат даты");
            }
            Date newDate = new Date(Integer.parseInt(newDataEndField.getText().substring(0,4)) - 1900,
                    Integer.parseInt(newDataEndField.getText().substring(5,7)) - 1,
                    Integer.parseInt(newDataEndField.getText().substring(8,10)));
            if (priceField.getText().isEmpty()) {
                throw new IllegalArgumentException("Внесите оплату");
            }
            for(Character character: priceField.getText().toCharArray()) {
                if(!Character.isDigit(character)) {
                    throw new IllegalArgumentException("Неверый формат введённой суммы");
                }
            }
            Booking booking = bookingController.getBookingByNumRoom(roomField.getText());
            int pr = roomController.getPriceRoomByNum(Integer.parseInt(roomField.getText()));
            pr = pr * HelpFunction.getDayBetweenDate(booking.getDateOfDeparture(), newDate);
            if (Integer.parseInt(priceField.getText()) < pr) {
                throw new IllegalArgumentException("Недостаточно. Цена: " + pr);
            } else if (Integer.parseInt(priceField.getText()) > pr) {
                backPr = Integer.parseInt(priceField.getText()) - pr;
            }
            bookingController.extendBooking(Integer.parseInt(roomField.getText()), newDate);
        } catch (Exception ex) {
            flag = false;
            info.setText(ex.getMessage());
        }
        if (flag) {
            if(backPr == 0) {
                info.setText("Номер продлён");
            } else {
                info.setText("Номер продлён. Сдача: " + backPr + " руб.");
            }
        }
    }

}
