package org.stolbovik.database.hotel.GUI.Panels.Admins;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.GUI.Listeners.BackListeners.BackToLoggingListener;
import org.stolbovik.database.hotel.GUI.MainFrame;
import org.stolbovik.database.hotel.utils.Constatns;

import javax.swing.*;
import java.awt.*;

public class AdminMenuPanel extends JPanel {

    private final JButton accept = new JButton("Принять жалобу клиента");
    private final JButton checkIn = new JButton("Заселить гостя");
    private final JButton newEmployee = new JButton("Нанять нового сотрудника");
    private final JButton fire = new JButton("Уволить сотрудника");
    private final JButton back = new JButton("Назад");
    private final MainFrame mainFrame;

    public AdminMenuPanel(@NotNull MainFrame mainFrame) {
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
        JPanel acceptPanel = new JPanel();
        acceptPanel.add(accept);
        JPanel checkInPanel = new JPanel();
        checkInPanel.add(checkIn);
        JPanel newEmployeePanel = new JPanel();
        newEmployeePanel.add(newEmployee);
        JPanel firePanel = new JPanel();
        firePanel.add(fire);
        JPanel backPanel = new JPanel();
        back.addActionListener(new BackToLoggingListener(mainFrame));
        backPanel.add(back);
        add(acceptPanel, new GridBagConstraints(0, 0, 1, 1, 1, 1,GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(checkInPanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(newEmployeePanel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(firePanel, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(backPanel, new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
    }
}
