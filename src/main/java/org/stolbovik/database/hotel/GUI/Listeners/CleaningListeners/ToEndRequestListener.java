package org.stolbovik.database.hotel.GUI.Listeners.CleaningListeners;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.GUI.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToEndRequestListener implements ActionListener {

    private final MainFrame mainFrame;

    public ToEndRequestListener(@NotNull MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainFrame.setEndRequestPanel();
    }

}
