package org.stolbovik.database.hotel.GUI.Listeners;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.GUI.MainFrame;
import org.stolbovik.database.hotel.controller.UserController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackToLoggingListener implements ActionListener {

    private final MainFrame mainFrame;

    public BackToLoggingListener(@NotNull MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainFrame.setAuthorization();
    }

}
