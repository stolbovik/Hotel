package org.stolbovik.database.hotel.GUI.Listeners.AdminListeners;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.GUI.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToAcceptComplaintListener implements ActionListener {

    private final MainFrame mainFrame;

    public ToAcceptComplaintListener(@NotNull MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainFrame.setAcceptComplaintPanel();
    }

}
