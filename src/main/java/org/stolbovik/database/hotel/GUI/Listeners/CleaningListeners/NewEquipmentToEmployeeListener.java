package org.stolbovik.database.hotel.GUI.Listeners.CleaningListeners;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.controller.EmployeeController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewEquipmentToEmployeeListener implements ActionListener {

    private final JTextField equipmentField;
    private final JPasswordField passportField;
    private final JLabel info;
    private final EmployeeController employeeController;

    public NewEquipmentToEmployeeListener(@NotNull JPasswordField passportField,
                                          @NotNull JLabel info,
                                          @NotNull JTextField equipmentField) {
        this.passportField = passportField;
        this.info = info;
        this.equipmentField = equipmentField;
        this.employeeController = new EmployeeController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag = true;
        try {
            employeeController.assignEquipmentToEmployee(equipmentField.getText(), passportField.getText());
        } catch (Exception ex) {
            flag = false;
            info.setText(ex.getMessage());
        }
        if (flag) {
            info.setText("Оборудование назначено сотруднику");
        }
    }

}
