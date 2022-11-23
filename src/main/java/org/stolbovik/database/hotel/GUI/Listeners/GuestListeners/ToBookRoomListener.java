package org.stolbovik.database.hotel.GUI.Listeners.GuestListeners;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.GUI.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToBookRoomListener implements ActionListener {

    private final MainFrame mainFrame;

    public ToBookRoomListener(@NotNull MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainFrame.setBookRoomPanel();
    }
}
