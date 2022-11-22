package org.stolbovik.database.hotel.GUI.Listeners.AuthorizationListeners;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.GUI.MainFrame;
import org.stolbovik.database.hotel.controller.UserController;
import org.stolbovik.database.hotel.models.Role;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoggingButtonActionListener implements ActionListener {

    private final JTextField login;
    private final JPasswordField password;
    private final JLabel info;
    private final UserController userController;
    private final MainFrame mainFrame;

    public LoggingButtonActionListener(@NotNull JTextField login,
                                       @NotNull JPasswordField password,
                                       @NotNull JLabel info,
                                       @NotNull MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.login = login;
        this.password = password;
        this.info = info;
        this.userController = new UserController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag = true;
        String loginString = login.getText();
        String passwordString = password.getText();
        Role role = null;
        try {
            role = userController.loginIn(loginString, passwordString);
        } catch (Exception ex) {
            flag = false;
            info.setText(ex.getMessage());
        }
        if (flag) {
            String rol = role.getRole();
            info.setText("Вы " + rol);
        }
        if (info.getText().substring(3).equals("Гость")) {
            mainFrame.setGuestMenu();
        } else if (info.getText().substring(3).equals("Администратор")) {
            mainFrame.setAdminMenu();
        } else if (info.getText().substring(3).equals("Главный клининг менеджер")) {
            mainFrame.setCleaningMenu();
        } else if (info.getText().substring(3).equals("Финансовый менеджер")) {
            mainFrame.setFinanceMenu();
        }
    }
}
