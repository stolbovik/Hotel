package org.stolbovik.database.hotel.GUI.Panels.Admins;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.GUI.Listeners.AdminListeners.BackToAdminMenuListener;
import org.stolbovik.database.hotel.GUI.Listeners.GuestListeners.BackToGuestMenuListener;
import org.stolbovik.database.hotel.GUI.MainFrame;
import org.stolbovik.database.hotel.utils.Constatns;

import javax.swing.*;
import java.awt.*;

public class CheckInGuestPanel extends JPanel {

    private final JLabel passportLabel = new JLabel("Паспортные данные");
    private final JLabel FIOLabel = new JLabel("ФИО");
    private final JLabel dataEndLabel = new JLabel("Дата выезда (ГГГГ-ММ-ДД)");
    private final JPasswordField passportField = new JPasswordField(20);
    private final JTextField FIOField = new JTextField(20);
    private final JTextField dataEndField = new JTextField(20);
    private final JButton request = new JButton("Заселить");
    private final JButton back = new JButton("Назад");
    private final JLabel info = (new JLabel(""));
    private final MainFrame mainFrame;

    public CheckInGuestPanel(@NotNull MainFrame mainFrame) {
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
        JPanel passportLabelPanel = new JPanel();
        passportLabelPanel.add(passportLabel);
        JPanel FIOLabelPanel = new JPanel();
        FIOLabelPanel.add(FIOLabel);
        JPanel dataEndLabelPanel = new JPanel();
        dataEndLabelPanel.add(dataEndLabel);
        JPanel passportFieldPanel = new JPanel();
        passportFieldPanel.add(passportField);
        JPanel FIOFieldPanel = new JPanel();
        FIOFieldPanel.add(FIOField);
        JPanel dataEndFieldPanel = new JPanel();
        dataEndFieldPanel.add(dataEndField);
        JPanel requestPanel = new JPanel();
        requestPanel.add(request);
        JPanel infoPanel = new JPanel();
        infoPanel.add(info);
        JPanel backPanel = new JPanel();
        back.addActionListener(new BackToAdminMenuListener(mainFrame));
        backPanel.add(back);
        add(passportLabelPanel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(FIOLabelPanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(dataEndLabelPanel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(passportFieldPanel, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(FIOFieldPanel, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(dataEndFieldPanel, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER,
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
