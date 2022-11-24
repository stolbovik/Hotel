package org.stolbovik.database.hotel.GUI.Listeners.FinanceListeners;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.controller.EmployeeController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePrizeListener implements ActionListener {

    private final JRadioButton up;
    private final JRadioButton down;
    private final JPasswordField passportField;
    private final JTextField precentField;
    private final JLabel info;
    private final EmployeeController employeeController;

    public ChangePrizeListener(@NotNull JRadioButton up,
                               @NotNull JRadioButton down,
                               @NotNull JPasswordField passportField,
                               @NotNull JTextField precentField,
                               @NotNull JLabel info) {
        this.up = up;
        this.down = down;
        this.passportField = passportField;
        this.precentField = precentField;
        this.info = info;
        this.employeeController = new EmployeeController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag = true;
        String command = "";
        try {
            if (up.isSelected()) {
                command = "Повысить";
            } else if (down.isSelected()) {
                command = "Понизить";
            } else {
                throw new IllegalArgumentException("Не выбрано повысить или понизить");
            }
            for (int i = 0; i < precentField.getText().length(); i++) {
                if (!Character.isDigit(precentField.getText().charAt(i))) {
                    throw new IllegalArgumentException("Неверное значения премии");
                }
            }
            employeeController.changePrize(passportField.getText(), Integer.parseInt(precentField.getText()), command);
        } catch (Exception ex) {
            flag = false;
            info.setText(ex.getMessage());
        }
        if (flag) {
            if (command.equals("Повысить")) {
                info.setText("Повысили премию на " + precentField.getText() + " процентов");
            } else {
                info.setText("Понизили премию на " + precentField.getText() + " процентов");
            }
        }
    }

}
