package org.stolbovik.database.hotel.GUI.Panels.Cleanings;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.GUI.Listeners.BackListeners.BackToLoggingListener;
import org.stolbovik.database.hotel.GUI.MainFrame;
import org.stolbovik.database.hotel.utils.Constatns;

import javax.swing.*;
import java.awt.*;

public class CleaningManagerMenuPanel extends JPanel {

    private final JButton employeeOnRequest = new JButton("Назначить работника на заявку");
    private final JButton endRequest = new JButton("Закончить уборку");
    private final JButton equipmentOnEmployee = new JButton("Назначить спец.оборудование");
    private final JButton deleteEquipmentOnEmployee = new JButton("Освободить от спец.оборудования");
    private final JButton back = new JButton("Назад");
    private final MainFrame mainFrame;

    public CleaningManagerMenuPanel(@NotNull MainFrame mainFrame) {
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
        JPanel employeeOnRequestPanel = new JPanel();
        employeeOnRequestPanel.add(employeeOnRequest);
        JPanel endRequestPanel = new JPanel();
        endRequestPanel.add(endRequest);
        JPanel equipmentOnEmployeePanel = new JPanel();
        equipmentOnEmployeePanel.add(equipmentOnEmployee);
        JPanel deleteEquipmentOnEmployeePanel = new JPanel();
        deleteEquipmentOnEmployeePanel.add(deleteEquipmentOnEmployee);
        JPanel backPanel = new JPanel();
        back.addActionListener(new BackToLoggingListener(mainFrame));
        backPanel.add(back);
        add(employeeOnRequestPanel, new GridBagConstraints(0, 0, 1, 1, 1, 1,GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(endRequestPanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(equipmentOnEmployeePanel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(deleteEquipmentOnEmployeePanel, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(backPanel, new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
    }

}
