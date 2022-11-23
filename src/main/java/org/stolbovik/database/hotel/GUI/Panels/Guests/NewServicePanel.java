package org.stolbovik.database.hotel.GUI.Panels.Guests;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.GUI.Listeners.GuestListeners.BackToGuestMenuListener;
import org.stolbovik.database.hotel.GUI.MainFrame;
import org.stolbovik.database.hotel.controller.PaidServiceController;
import org.stolbovik.database.hotel.service.PaidServiceService;
import org.stolbovik.database.hotel.utils.Constatns;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewServicePanel extends JPanel {

    private final JLabel serviceLabel = new JLabel("Название услуги");
    private final JLabel passportLabel = new JLabel("Серия и номер паспорта");

    private final JLabel dateOfServiceLabel = new JLabel("Дата исполнения(ГГГГ-ММ-ДД)");
    private final JTextField serviceField = new JTextField(15);
    private final JPasswordField passportField = new JPasswordField(15);
    private final JTextField dateOfServiceField = new JTextField(15);
    private final JButton request = new JButton("Заказать");
    private final JButton back = new JButton("Назад");
    private final JLabel info = (new JLabel(""));
    private final MainFrame mainFrame;

    public NewServicePanel(@NotNull MainFrame mainFrame) {
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
        PaidServiceController equipmentController = new PaidServiceController();
        java.util.List<String> list;
        try {
            list = equipmentController.getAllPaidService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String[] arrayname = list.toArray(new String[list.size()]);
        JList servicesList = new JList(arrayname);
        JScrollPane servicePane = new JScrollPane(servicesList);
        JPanel serviceLabelPanel = new JPanel();
        serviceLabelPanel.add(serviceLabel);
        JPanel passportLabelPanel = new JPanel();
        passportLabelPanel.add(passportLabel);
        JPanel dateOfServiceLabelPanel = new JPanel();
        dateOfServiceLabelPanel.add(dateOfServiceLabel);
        JPanel serviceFieldPanel = new JPanel();
        serviceFieldPanel.add(serviceField);
        JPanel passportFieldPanel = new JPanel();
        passportFieldPanel.add(passportField);
        JPanel dateOfServiceFieldPanel = new JPanel();
        dateOfServiceFieldPanel.add(dateOfServiceField);
        JPanel serviceScrollPanel = new JPanel();
        serviceScrollPanel.add(servicePane);
        JPanel requestPanel = new JPanel();
        requestPanel.add(request);
        JPanel infoPanel = new JPanel();
        infoPanel.add(info);
        JPanel backPanel = new JPanel();
        back.addActionListener(new BackToGuestMenuListener(mainFrame));
        backPanel.add(back);
        add(serviceLabelPanel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(passportLabelPanel, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(dateOfServiceLabelPanel, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(serviceFieldPanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(passportFieldPanel, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(dateOfServiceFieldPanel, new GridBagConstraints(2, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(serviceScrollPanel, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(infoPanel, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(requestPanel, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(backPanel, new GridBagConstraints(2, 3, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
    }

    public void setInfoLabel(String text) {
        info.setText(text);
    }

}
