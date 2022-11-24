package org.stolbovik.database.hotel.GUI.Panels.Guests;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.GUI.Listeners.GuestListeners.BackToGuestMenuListener;
import org.stolbovik.database.hotel.GUI.Listeners.GuestListeners.BookRoomListener;
import org.stolbovik.database.hotel.GUI.MainFrame;
import org.stolbovik.database.hotel.controller.PaidServiceController;
import org.stolbovik.database.hotel.utils.Constatns;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class BookRoomPanel extends JPanel {

    private final JLabel passsportLabel = new JLabel("Серия и номер паспорта");
    private final JLabel FIOLabel = new JLabel("ФИО");
    private final JLabel dataStartLabel = new JLabel("Дата заезда (ГГГГ-ММ-ДД)");
    private final JLabel dataEndLabel = new JLabel("Дата выезда (ГГГГ-ММ-ДД)");
    private final JPasswordField passportField = new JPasswordField(20);
    private final JTextField FIOField = new JTextField(20);
    private final JTextField dataStartField = new JTextField(20);
    private final JTextField dataEndField = new JTextField(20);
    private final JButton request = new JButton("Забронировать");
    private final JButton back = new JButton("Назад");
    private final JLabel info = (new JLabel(""));
    private final MainFrame mainFrame;

    public BookRoomPanel(@NotNull MainFrame mainFrame) {
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
        List<String> list;
        try {
            list = equipmentController.getAllPaidServiceWithPrice();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String[] arrayname = list.toArray(new String[list.size()]);
        JList servicesList = new JList(arrayname);
        JScrollPane servicePane = new JScrollPane(servicesList);

        JPanel passsportLabelPanel = new JPanel();
        passsportLabelPanel.add(passsportLabel);
        JPanel FIOLabelPanel = new JPanel();
        FIOLabelPanel.add(FIOLabel);
        JPanel dataStartLabelPanel = new JPanel();
        dataStartLabelPanel.add(dataStartLabel);
        JPanel dataEndLabelPanel = new JPanel();
        dataEndLabelPanel.add(dataEndLabel);
        JPanel passwordFieldPanel = new JPanel();
        passwordFieldPanel.add(passportField);
        JPanel FIOFieldPanel = new JPanel();
        FIOFieldPanel.add(FIOField);
        JPanel dataStartFieldPanel = new JPanel();
        dataStartFieldPanel.add(dataStartField);
        JPanel dataEndFieldPanel = new JPanel();
        dataEndFieldPanel.add(dataEndField);
        JPanel requestPanel = new JPanel();
        request.addActionListener(new BookRoomListener(info, FIOField, passportField, dataStartField, dataEndField));
        requestPanel.add(request);
        JPanel infoPanel = new JPanel();
        infoPanel.add(info);
        JPanel backPanel = new JPanel();
        back.addActionListener(new BackToGuestMenuListener(mainFrame));
        backPanel.add(back);
        add(passsportLabelPanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(FIOLabelPanel, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(dataStartLabelPanel, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(dataEndLabelPanel, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(passwordFieldPanel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(FIOFieldPanel, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(dataStartFieldPanel, new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(dataEndFieldPanel, new GridBagConstraints(1, 4, 1, 1, 1, 1, GridBagConstraints.NORTH,
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
