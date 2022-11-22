package org.stolbovik.database.hotel.GUI.Panels.Guests;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.GUI.Listeners.BackListeners.BackToLoggingListener;
import org.stolbovik.database.hotel.GUI.MainFrame;
import org.stolbovik.database.hotel.utils.Constatns;

import javax.swing.*;
import java.awt.*;

public class GuestMenuPanel extends JPanel {

    private final JButton brone = new JButton("Забронировать номер");
    private final JButton checkInto = new JButton("Заселиться в забронированный номер");
    private final JButton moveOut = new JButton("Выселиться");
    private final JButton extend = new JButton("Продлить номер");
    private final JButton newService = new JButton("Заказать платную услугу");
    private final JButton back = new JButton("Назад");
    private final MainFrame mainFrame;

    public GuestMenuPanel(@NotNull MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
        setBaseParameter();
        setComponentOnFrame();
    }

    private void setBaseParameter() {
        setPreferredSize(new Dimension(Constatns.WINDOW_WIDTH, Constatns.WINDOW_HEIGHT));
        setLayout(new GridBagLayout());
    }

    private void setComponentOnFrame() {
        JPanel bronePanel = new JPanel();
        bronePanel.add(brone);
        JPanel checkIntoPanel = new JPanel();
        checkIntoPanel.add(checkInto);
        JPanel moveOutPanel = new JPanel();
        moveOutPanel.add(moveOut);
        JPanel extendPanel = new JPanel();
        extendPanel.add(extend);
        JPanel newServicePanel = new JPanel();
        newServicePanel.add(newService);
        JPanel backPanel = new JPanel();
        back.addActionListener(new BackToLoggingListener(mainFrame));
        backPanel.add(back);
        add(bronePanel, new GridBagConstraints(0, 0, 1, 1, 1, 1,GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(checkIntoPanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(moveOutPanel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(extendPanel, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(newServicePanel, new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(backPanel, new GridBagConstraints(0, 5, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
    }

}
