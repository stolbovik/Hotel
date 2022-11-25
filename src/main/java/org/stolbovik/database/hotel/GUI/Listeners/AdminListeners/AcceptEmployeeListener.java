package org.stolbovik.database.hotel.GUI.Listeners.AdminListeners;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.controller.ComplaintBookController;
import org.stolbovik.database.hotel.controller.EmployeeController;
import org.stolbovik.database.hotel.utils.HelpFunction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class AcceptEmployeeListener implements ActionListener {

    private final JPasswordField passportField;
    private final JTextField FIOField;
    private final JTextField birthdayField;
    private final JTextField postFiled;
    private final JLabel info;
    private final EmployeeController employeeController;

    public AcceptEmployeeListener(@NotNull JPasswordField passportField,
                                   @NotNull JTextField FIOField,
                                   @NotNull JTextField birthdayField,
                                   @NotNull JTextField postFiled,
                                   @NotNull JLabel info) {
        this.info = info;
        this.passportField = passportField;
        this.FIOField = FIOField;
        this.birthdayField = birthdayField;
        this.postFiled = postFiled;
        this.employeeController = new EmployeeController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag = true;
        try {
            if (!HelpFunction.checkDataString(birthdayField.getText())) {
                throw new IllegalArgumentException("Неверный формат даты");
            }
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
            Date birthday = new Date(Integer.parseInt(birthdayField.getText().substring(0,4)) - 1900,
                    Integer.parseInt(birthdayField.getText().substring(5,7)) - 1,
                    Integer.parseInt(birthdayField.getText().substring(8,10)));
            System.out.println(firstName + lastName + fathername);
            employeeController.hireNewEmployee(passportField.getText(),firstName,lastName, firstName, birthday,postFiled.getText());
        } catch (Exception ex) {
            flag = false;
            info.setText(ex.getMessage());
        }
        if (flag) {
            info.setText("Сотрудник принят");
        }
    }

}
