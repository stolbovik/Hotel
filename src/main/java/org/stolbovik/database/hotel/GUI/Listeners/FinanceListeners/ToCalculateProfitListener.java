package org.stolbovik.database.hotel.GUI.Listeners.FinanceListeners;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.GUI.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToCalculateProfitListener implements ActionListener {

    private final MainFrame mainFrame;

    public ToCalculateProfitListener(@NotNull MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainFrame.setCalculateProfitPanel();

    }
}
