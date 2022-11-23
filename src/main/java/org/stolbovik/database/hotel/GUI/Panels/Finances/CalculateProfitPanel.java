package org.stolbovik.database.hotel.GUI.Panels.Finances;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.GUI.Listeners.FinanceListeners.BackToFinanceMenuListener;
import org.stolbovik.database.hotel.GUI.MainFrame;
import org.stolbovik.database.hotel.utils.Constatns;

import javax.swing.*;
import java.awt.*;

public class CalculateProfitPanel extends JPanel {

    private final JLabel dataStartLabel = new JLabel("Начальная дата (ГГГГ-ММ-ДД)");
    private final JLabel dataEndLabel = new JLabel("Конечная дата (ГГГГ-ММ-ДД)");
    private final JTextField dataStartField = new JTextField(20);
    private final JTextField dataEndField = new JTextField(20);
    private final JButton request = new JButton("Посчитать");
    private final JButton back = new JButton("Назад");
    private final JLabel info = (new JLabel(""));
    private final MainFrame mainFrame;

    public CalculateProfitPanel(@NotNull MainFrame mainFrame) {
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
        JPanel dataStartLabelPanel = new JPanel();
        dataStartLabelPanel.add(dataStartLabel);
        JPanel dataEndLabelPanel = new JPanel();
        dataEndLabelPanel.add(dataEndLabel);
        JPanel dataStartFieldPanel = new JPanel();
        dataStartFieldPanel.add(dataStartField);
        JPanel dataEndFieldPanel = new JPanel();
        dataEndFieldPanel.add(dataEndField);
        JPanel requestPanel = new JPanel();
        requestPanel.add(request);
        JPanel infoPanel = new JPanel();
        infoPanel.add(info);
        JPanel backPanel = new JPanel();
        back.addActionListener(new BackToFinanceMenuListener(mainFrame));
        backPanel.add(back);
        add(dataStartLabelPanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(dataEndLabelPanel, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(dataStartFieldPanel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(dataEndFieldPanel, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.NORTH,
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
