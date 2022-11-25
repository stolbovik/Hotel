package org.stolbovik.database.hotel.GUI.Listeners.AdminListeners;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.controller.EmployeeController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FireEmployeeListener implements ActionListener {

    private final JPasswordField passportField;
    private final JLabel info;
    private final EmployeeController employeeController;

    public FireEmployeeListener(@NotNull JPasswordField passportField,
                                @NotNull JLabel info) {
        this.info = info;
        this.passportField = passportField;
        this.employeeController = new EmployeeController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag = true;
        try {
            employeeController.fireAnEmployee(passportField.getText());
        } catch (Exception ex) {
            flag = false;
            info.setText(ex.getMessage());
        }
        if (flag) {
            info.setText("Сотрудник уволен");
        }
    }

}
