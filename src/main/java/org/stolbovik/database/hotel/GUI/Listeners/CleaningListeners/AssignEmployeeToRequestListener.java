package org.stolbovik.database.hotel.GUI.Listeners.CleaningListeners;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.controller.RequestForCleaningController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AssignEmployeeToRequestListener implements ActionListener {

    private final JTextField requestField;
    private final JLabel info;
    private final RequestForCleaningController requestForCleaningController;

    public AssignEmployeeToRequestListener(@NotNull JTextField requestField,
                              @NotNull JLabel info) {
        this.info = info;
        this.requestField = requestField;
        this.requestForCleaningController = new RequestForCleaningController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag = true;
        try {
            for (int i = 0; i < requestField.getText().length(); i++) {
                if (!Character.isDigit(requestField.getText().charAt(i))) {
                    throw new IllegalArgumentException("Неверный номер заявки");
                }
            }
            requestForCleaningController.assignEmployeeToRequest(Integer.parseInt(requestField.getText()));
        } catch (Exception ex) {
            flag = false;
            info.setText(ex.getMessage());
        }
        if (flag) {
            info.setText("Сотрудник назначен");
        }
    }

}
