package org.stolbovik.database.hotel.GUI.Panels.Guests;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.GUI.Listeners.GuestListeners.BackToGuestMenuListener;
import org.stolbovik.database.hotel.GUI.MainFrame;
import org.stolbovik.database.hotel.utils.Constatns;

import javax.swing.*;
import java.awt.*;

public class ExtendRoomPanel extends JPanel {
    private final JLabel roomLabel = new JLabel("Номер комнаты");

    private final JLabel newDataEndLabel = new JLabel("Новая дата выезда (ГГГГ-ММ-ДД)");
    private final JTextField roomField = new JTextField(20);
    private final JTextField newDataEndField = new JTextField(20);
    private final JButton request = new JButton("Продлить");
    private final JButton back = new JButton("Назад");
    private final JLabel info = (new JLabel(""));
    private final MainFrame mainFrame;

    public ExtendRoomPanel(@NotNull MainFrame mainFrame) {
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
        JPanel roomLabelPanel = new JPanel();
        roomLabelPanel.add(roomLabel);
        JPanel newDataEndLabelPanel = new JPanel();
        newDataEndLabelPanel.add(newDataEndLabel);
        JPanel roomFieldPanel = new JPanel();
        roomFieldPanel.add(roomField);
        JPanel newDataEndFieldPanel = new JPanel();
        newDataEndFieldPanel.add(newDataEndField);
        JPanel requestPanel = new JPanel();
        requestPanel.add(request);
        JPanel infoPanel = new JPanel();
        infoPanel.add(info);
        JPanel backPanel = new JPanel();
        back.addActionListener(new BackToGuestMenuListener(mainFrame));
        backPanel.add(back);
        add(roomLabelPanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(newDataEndLabelPanel, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(roomFieldPanel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(newDataEndFieldPanel, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(infoPanel, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(requestPanel, new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(backPanel, new GridBagConstraints(1, 4, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
    }

    public void setInfoLabel(String text) {
        info.setText(text);
    }

}
