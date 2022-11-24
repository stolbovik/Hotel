package org.stolbovik.database.hotel.GUI.Listeners.CleaningListeners;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.controller.EmployeeController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReleazeEmployeeFromEquipmentListener implements ActionListener {

    private final JPasswordField passportField;
    private final JLabel info;
    private final EmployeeController employeeController;

    public ReleazeEmployeeFromEquipmentListener(@NotNull JPasswordField passportField,
                                                @NotNull JLabel info) {
        this.passportField = passportField;
        this.info = info;
        this.employeeController = new EmployeeController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag = true;
        try {
            employeeController.releaseEmployeeFromEquipment(passportField.getText());
        } catch (Exception ex) {
            flag = false;
            info.setText(ex.getMessage());
        }
        if (flag) {
            info.setText("Сотрудник освобождем от оборудования");
        }
    }

}
