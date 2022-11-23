package org.stolbovik.database.hotel.GUI.Listeners.GuestListeners;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.GUI.MainFrame;
import org.stolbovik.database.hotel.GUI.Panels.Admins.AdminMenuPanel;
import org.stolbovik.database.hotel.GUI.Panels.Cleanings.CleaningManagerMenuPanel;
import org.stolbovik.database.hotel.GUI.Panels.Finances.FinanceMenuPanel;
import org.stolbovik.database.hotel.GUI.Panels.Guests.GuestMenuPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackToGuestMenuListener implements ActionListener {

    private final MainFrame mainFrame;

    public BackToGuestMenuListener(@NotNull MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainFrame.setGuestMenu();
    }

}
