package org.stolbovik.database.hotel.GUI.Panels.Guests;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.GUI.Listeners.GuestListeners.BackToGuestMenuListener;
import org.stolbovik.database.hotel.GUI.Listeners.GuestListeners.CheckIntoRoomListener;
import org.stolbovik.database.hotel.GUI.MainFrame;
import org.stolbovik.database.hotel.utils.Constatns;

import javax.swing.*;
import java.awt.*;

public class CheckIntoRoomPanel extends JPanel {

    private final JLabel passsportLabel = new JLabel("Серия и номер паспорта");
    private final JLabel priceLabel = new JLabel("Оплатите проживание");
    private final JPasswordField passportField = new JPasswordField(20);
    private final JTextField priceField = new JPasswordField(20);
    private final JButton request = new JButton("Запросить ключ");
    private final JButton back = new JButton("Назад");
    private final JLabel info = (new JLabel(""));
    private final MainFrame mainFrame;

    public CheckIntoRoomPanel(@NotNull MainFrame mainFrame) {
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
        JPanel passsportLabelPanel = new JPanel();
        passsportLabelPanel.add(passsportLabel);
        JPanel priceLabelPanel = new JPanel();
        priceLabelPanel.add(priceLabel);
        JPanel priceFieldPanel = new JPanel();
        priceFieldPanel.add(priceField);
        JPanel passwordFieldPanel = new JPanel();
        passwordFieldPanel.add(passportField);
        request.addActionListener(new CheckIntoRoomListener(info, priceField, passportField));
        JPanel requestPanel = new JPanel();
        requestPanel.add(request);
        JPanel infoPanel = new JPanel();
        infoPanel.add(info);
        JPanel backPanel = new JPanel();
        back.addActionListener(new BackToGuestMenuListener(mainFrame));
        backPanel.add(back);
        add(passsportLabelPanel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(passwordFieldPanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(priceLabelPanel, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(priceFieldPanel, new GridBagConstraints(2, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(infoPanel, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(requestPanel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(backPanel, new GridBagConstraints(2, 2, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
    }

    public void setInfoLabel(String text) {
        info.setText(text);
    }

}
