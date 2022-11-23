package org.stolbovik.database.hotel.GUI.Panels.Finances;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.GUI.Listeners.BackToLoggingListener;
import org.stolbovik.database.hotel.GUI.Listeners.FinanceListeners.ToCalculateProfitListener;
import org.stolbovik.database.hotel.GUI.Listeners.FinanceListeners.ToCalculateSalariesListener;
import org.stolbovik.database.hotel.GUI.Listeners.FinanceListeners.ToChangePrizeListeneer;
import org.stolbovik.database.hotel.GUI.MainFrame;
import org.stolbovik.database.hotel.utils.Constatns;

import javax.swing.*;
import java.awt.*;

public class FinanceMenuPanel extends JPanel {

    private final JButton salary = new JButton("Посчитать затраты на зарплаты");
    private final JButton rooms = new JButton("Посчитать прибыль от номеров");
    private final JButton prize = new JButton("Изменить премию сотруднику");
    private final JButton back = new JButton("Назад");
    private final MainFrame mainFrame;

    public FinanceMenuPanel(@NotNull MainFrame mainFrame) {
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
        JPanel salaryPanel = new JPanel();
        salary.addActionListener(new ToCalculateSalariesListener(mainFrame));
        salaryPanel.add(salary);
        JPanel roomsPanel = new JPanel();
        rooms.addActionListener(new ToCalculateProfitListener(mainFrame));
        roomsPanel.add(rooms);
        JPanel prizePanel = new JPanel();
        prize.addActionListener(new ToChangePrizeListeneer(mainFrame));
        prizePanel.add(prize);
        JPanel backPanel = new JPanel();
        back.addActionListener(new BackToLoggingListener(mainFrame));
        backPanel.add(back);
        add(salaryPanel, new GridBagConstraints(0, 0, 1, 1, 1, 1,GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(roomsPanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(prizePanel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(backPanel, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
    }


}
