package org.stolbovik.database.hotel.GUI.Listeners.GuestListeners;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.controller.OrderedServiceController;
import org.stolbovik.database.hotel.controller.PaidServiceController;
import org.stolbovik.database.hotel.utils.HelpFunction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class OrderServiceListener implements ActionListener {

    private final JLabel info;
    private final JTextField serviceField;
    private final JPasswordField passportField;
    private final JTextField dateOfServiceField;
    private final JTextField priceFied;
    private final OrderedServiceController orderedServiceController;
    private final PaidServiceController paidServiceController;


    public OrderServiceListener(@NotNull JLabel info,
                                @NotNull JTextField serviceField,
                                @NotNull JPasswordField passportField,
                                @NotNull JTextField dateOfServiceField,
                                @NotNull JTextField priceFied) {
        this.info = info;
        this.serviceField = serviceField;
        this.passportField = passportField;
        this.dateOfServiceField = dateOfServiceField;
        this.priceFied = priceFied;
        this.orderedServiceController = new OrderedServiceController();
        this.paidServiceController = new PaidServiceController();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag = true;
        int backPr = 0;
        try {
           if (!HelpFunction.checkDataString(dateOfServiceField.getText())) {
               throw new IllegalArgumentException("Неверный формат даты");
           }
            Date dateOfService = new Date(Integer.parseInt(dateOfServiceField.getText().substring(0,4)) - 1900,
                    Integer.parseInt(dateOfServiceField.getText().substring(5,7)) - 1,
                    Integer.parseInt(dateOfServiceField.getText().substring(8,10)));
           if (priceFied.getText().isEmpty()) {
               throw new IllegalArgumentException("Внесите оплату");
           }
            for(Character character: priceFied.getText().toCharArray()) {
                if(!Character.isDigit(character)) {
                    throw new IllegalArgumentException("Неверый формат введённой суммы");
                }
            }
            int pr = paidServiceController.getPaidServiceByName(serviceField.getText()).getPrice();
            if (Integer.parseInt(priceFied.getText()) < pr) {
                throw new IllegalArgumentException("Недостаточно средств");
            } else if (Integer.parseInt(priceFied.getText()) > pr) {
                backPr = Integer.parseInt(priceFied.getText()) - pr;
            }
            orderedServiceController.orderPaidService(serviceField.getText(), passportField.getText(), dateOfService);
        } catch (Exception ex) {
            flag = false;
            info.setText(ex.getMessage());
        }
        if (flag) {
            if (backPr == 0) {
                info.setText("Услуга заказана");
            } else {
                info.setText("Услуга заказана. Cдача: " + backPr + " рублей");
            }
        }
    }

}
