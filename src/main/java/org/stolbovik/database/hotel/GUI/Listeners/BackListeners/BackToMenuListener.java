package org.stolbovik.database.hotel.GUI.Listeners.BackListeners;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.GUI.MainFrame;
import org.stolbovik.database.hotel.GUI.Panels.Admins.AdminMenuPanel;
import org.stolbovik.database.hotel.GUI.Panels.Cleanings.CleaningManagerMenuPanel;
import org.stolbovik.database.hotel.GUI.Panels.Finances.FinanceMenuPanel;
import org.stolbovik.database.hotel.GUI.Panels.Guests.GuestMenuPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackToMenuListener implements ActionListener {

    private final MainFrame mainFrame;
    private final JPanel panel;

    public BackToMenuListener(@NotNull MainFrame mainFrame,
                              @NotNull JPanel panel) {
        this.mainFrame = mainFrame;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (panel instanceof AdminMenuPanel) {
            mainFrame.setAdminMenu();
        } else if (panel instanceof CleaningManagerMenuPanel) {
            mainFrame.setCleaningMenu();
        } else if (panel instanceof FinanceMenuPanel) {
            mainFrame.setFinanceMenu();
        } else if (panel instanceof GuestMenuPanel) {
            mainFrame.setGuestMenu();
        }
    }

}
