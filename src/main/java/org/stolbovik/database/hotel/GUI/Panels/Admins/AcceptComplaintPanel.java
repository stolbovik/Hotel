package org.stolbovik.database.hotel.GUI.Panels.Admins;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.GUI.Listeners.AdminListeners.AcceptComplaintListener;
import org.stolbovik.database.hotel.GUI.Listeners.AdminListeners.BackToAdminMenuListener;
import org.stolbovik.database.hotel.GUI.MainFrame;
import org.stolbovik.database.hotel.utils.Constatns;

import javax.swing.*;
import java.awt.*;

public class AcceptComplaintPanel extends JPanel {

    private final JLabel passportLabel = new JLabel("Серия и номер паспорт клиента");
    private final JLabel complaintLabel = new JLabel("Жалоба клиента");
    private final JLabel answerLabel = new JLabel("Ответ администратора");
    private final JPasswordField passportField = new JPasswordField(20);
    private final JTextField complaintField = new JTextField(20);
    private final JTextField answerField = new JTextField(20);
    private final JButton request = new JButton("Занести в книгу");
    private final JButton back = new JButton("Назад");
    private final JLabel info = (new JLabel(""));
    private final MainFrame mainFrame;

    public AcceptComplaintPanel(@NotNull MainFrame mainFrame) {
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
        JPanel complaintLabelPanel = new JPanel();
        complaintLabelPanel.add(complaintLabel);
        JPanel answerLabelPanel = new JPanel();
        answerLabelPanel.add(answerLabel);
        JPanel passportFieldPanel = new JPanel();
        passportFieldPanel.add(passportField);
        JPanel complaintFieldPanel = new JPanel();
        complaintFieldPanel.add(complaintField);
        JPanel answerFieldPanel = new JPanel();
        answerFieldPanel.add(answerField);
        JPanel requestPanel = new JPanel();
        request.addActionListener(new AcceptComplaintListener(passportField, complaintField, answerField, info));
        requestPanel.add(request);
        JPanel infoPanel = new JPanel();
        infoPanel.add(info);
        JPanel backPanel = new JPanel();
        back.addActionListener(new BackToAdminMenuListener(mainFrame));
        backPanel.add(back);
        add(passportLabelPanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.EAST,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(passportFieldPanel, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(complaintLabelPanel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(complaintFieldPanel, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(answerLabelPanel, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(answerFieldPanel, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.NORTH,
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
