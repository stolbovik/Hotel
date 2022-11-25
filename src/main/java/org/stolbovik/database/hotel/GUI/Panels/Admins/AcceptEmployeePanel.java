package org.stolbovik.database.hotel.GUI.Panels.Admins;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.GUI.Listeners.AdminListeners.AcceptEmployeeListener;
import org.stolbovik.database.hotel.GUI.Listeners.AdminListeners.BackToAdminMenuListener;
import org.stolbovik.database.hotel.GUI.MainFrame;
import org.stolbovik.database.hotel.utils.Constatns;

import javax.swing.*;
import java.awt.*;

public class AcceptEmployeePanel extends JPanel {

    private final JLabel passsportLabel = new JLabel("Серия и номер паспорта");
    private final JLabel FIOLabel = new JLabel("ФИО");
    private final JLabel birthdayLabel = new JLabel("Дата рождения (ГГГГ-ММ-ДД)");
    private final JLabel postLabel = new JLabel("Должность");
    private final JPasswordField passportField = new JPasswordField(20);
    private final JTextField FIOField = new JTextField(20);
    private final JTextField birthdayField = new JTextField(20);
    private final JTextField postFiled = new JTextField(20);
    private final JButton request = new JButton("Принять");
    private final JButton back = new JButton("Назад");
    private final JLabel info = (new JLabel(""));
    private final MainFrame mainFrame;

    public AcceptEmployeePanel(@NotNull MainFrame mainFrame) {
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
        JPanel FIOLabelPanel = new JPanel();
        FIOLabelPanel.add(FIOLabel);
        JPanel birthdayLabelPanel = new JPanel();
        birthdayLabelPanel.add(birthdayLabel);
        JPanel postLabelPanel = new JPanel();
        postLabelPanel.add(postLabel);
        JPanel passwordFieldPanel = new JPanel();
        passwordFieldPanel.add(passportField);
        JPanel FIOFieldPanel = new JPanel();
        FIOFieldPanel.add(FIOField);
        JPanel birthdayFieldPanel = new JPanel();
        birthdayFieldPanel.add(birthdayField);
        JPanel postFiledPanel = new JPanel();
        postFiledPanel.add(postFiled);
        JPanel requestPanel = new JPanel();
        request.addActionListener(new AcceptEmployeeListener(passportField, FIOField, birthdayField, postFiled, info));
        requestPanel.add(request);
        JPanel infoPanel = new JPanel();
        infoPanel.add(info);
        JPanel backPanel = new JPanel();
        back.addActionListener(new BackToAdminMenuListener(mainFrame));
        backPanel.add(back);
        add(passsportLabelPanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(FIOLabelPanel, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(birthdayLabelPanel, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(postLabelPanel, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(passwordFieldPanel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(FIOFieldPanel, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(birthdayFieldPanel, new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(postFiledPanel, new GridBagConstraints(1, 4, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(infoPanel, new GridBagConstraints(0, 5, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(requestPanel, new GridBagConstraints(0, 6, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(backPanel, new GridBagConstraints(1, 6, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
    }

    public void setInfoLabel(String text) {
        info.setText(text);
    }


}
