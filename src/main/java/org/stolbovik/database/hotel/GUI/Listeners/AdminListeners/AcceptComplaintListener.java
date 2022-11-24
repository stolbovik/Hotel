package org.stolbovik.database.hotel.GUI.Listeners.AdminListeners;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.controller.ComplaintBookController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AcceptComplaintListener implements ActionListener {

    private final JPasswordField passportField;
    private final JTextField complaintField;
    private final JTextField answerField;
    private final JLabel info;
    private final ComplaintBookController complaintBookController;

    public AcceptComplaintListener(@NotNull JPasswordField passportField,
                                   @NotNull JTextField complaintField,
                                   @NotNull JTextField answerField,
                                   @NotNull JLabel info) {
        this.info = info;
        this.passportField = passportField;
        this.complaintField = complaintField;
        this.answerField = answerField;
        this.complaintBookController = new ComplaintBookController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag = true;
        try {
            complaintBookController.acceptComplaintOfClient(passportField.getText(),
                    complaintField.getText(), answerField.getText());
        } catch (Exception ex) {
            flag = false;
            info.setText(ex.getMessage());
            ex.printStackTrace();
        }
        if (flag) {
            info.setText("Жалоба добавлена");
        }
    }

}
