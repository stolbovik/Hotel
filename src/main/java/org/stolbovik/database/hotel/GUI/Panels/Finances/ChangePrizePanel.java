package org.stolbovik.database.hotel.GUI.Panels.Finances;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.GUI.Listeners.AdminListeners.BackToAdminMenuListener;
import org.stolbovik.database.hotel.GUI.Listeners.FinanceListeners.BackToFinanceMenuListener;
import org.stolbovik.database.hotel.GUI.MainFrame;
import org.stolbovik.database.hotel.utils.Constatns;

import javax.swing.*;
import java.awt.*;

public class ChangePrizePanel extends JPanel {

    private final JLabel passsportLabel = new JLabel("Серия и номер паспорта");
    private final JRadioButton up = new JRadioButton("Повысить");
    private final JRadioButton down = new JRadioButton("Понизить");
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private final JLabel precentLabel = new JLabel("На сколько процентов");
    private final JPasswordField passportField = new JPasswordField(20);
    private final JTextField precentField = new JTextField(20);
    private final JButton request = new JButton("Изменить");
    private final JButton back = new JButton("Назад");
    private final JLabel info = (new JLabel(""));
    private final MainFrame mainFrame;

    public ChangePrizePanel(@NotNull MainFrame mainFrame) {
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
        buttonGroup.add(up);
        buttonGroup.add(down);
        JPanel passsportLabelPanel = new JPanel();
        passsportLabelPanel.add(passsportLabel);
        JPanel precentLabelPanel = new JPanel();
        precentLabelPanel.add(precentLabel);
        JPanel upPanel = new JPanel();
        upPanel.add(up);
        JPanel downPanel = new JPanel();
        downPanel.add(down);
        JPanel passwordFieldPanel = new JPanel();
        passwordFieldPanel.add(passportField);
        JPanel precentFieldPanel = new JPanel();
        precentFieldPanel.add(precentField);
        JPanel requestPanel = new JPanel();
        requestPanel.add(request);
        JPanel infoPanel = new JPanel();
        infoPanel.add(info);
        JPanel backPanel = new JPanel();
        back.addActionListener(new BackToFinanceMenuListener(mainFrame));
        backPanel.add(back);
        add(passsportLabelPanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(passwordFieldPanel, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(upPanel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(downPanel, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(precentLabelPanel, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(precentFieldPanel, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(infoPanel, new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(requestPanel, new GridBagConstraints(0, 5, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(backPanel, new GridBagConstraints(1, 5, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
    }

    public void setInfoLabel(String text) {
        info.setText(text);
    }

}
