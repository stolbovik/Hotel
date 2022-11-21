package org.stolbovik.database.hotel.GUI.Listeners;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.controller.UserController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class LoggingButtonActionListeners implements ActionListener {

    private final JTextField login;
    private final JPasswordField password;
    private final JLabel info;
    private final UserController userController;

    public LoggingButtonActionListeners(@NotNull JTextField login,
                                        @NotNull JPasswordField password,
                                        @NotNull JLabel info) {
        this.login = login;
        this.password = password;
        this.info = info;
        this.userController = new UserController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String loginString = login.getText();
        String passwordString = password.getText();
        try {
            userController.loginIn(loginString, passwordString);
        } catch (Exception ex) {
            ex.printStackTrace();
            info.setText(ex.getMessage() + " " + loginString + " " + passwordString);
        }
    }
}
