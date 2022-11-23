package org.stolbovik.database.hotel.GUI.Panels.Cleanings;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.GUI.Listeners.CleaningListeners.BackToCleaningMenuListener;
import org.stolbovik.database.hotel.GUI.MainFrame;
import org.stolbovik.database.hotel.controller.EquipmentController;
import org.stolbovik.database.hotel.models.Equipment;
import org.stolbovik.database.hotel.utils.Constatns;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewEquipmentToEmployeePanel extends JPanel {

    private final JLabel equipmentLabel = new JLabel("Название");
    private final JLabel passportLabel = new JLabel("Паспорт сотрудника");
    private final JTextField equipmentField = new JTextField(15);
    private final JPasswordField passportField = new JPasswordField(15);
    private final JLabel servicesLabel = new JLabel("Список свободного");
    private final JButton request = new JButton("Заказать");
    private final JButton back = new JButton("Назад");
    private final JLabel info = (new JLabel(""));
    private final MainFrame mainFrame;

    public NewEquipmentToEmployeePanel(@NotNull MainFrame mainFrame) {
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
        EquipmentController equipmentController = new EquipmentController();
        List<String> list;
        try {
            list = equipmentController.getFreeEquipment();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String[] arrayname = list.toArray(new String[list.size()]);
        JList servicesList = new JList(arrayname);
        JScrollPane servicePane = new JScrollPane(servicesList);
        JPanel equipmentLabelPanel = new JPanel();
        equipmentLabelPanel.add(equipmentLabel);
        JPanel passportLabelPanel = new JPanel();
        passportLabelPanel.add(passportLabel);
        JPanel servicesLabelPanel = new JPanel();
        servicesLabelPanel.add(servicesLabel);
        JPanel equipmentFieldPanel = new JPanel();
        equipmentFieldPanel.add(equipmentField);
        JPanel passportFieldPanel = new JPanel();
        passportFieldPanel.add(passportField);
        JPanel servicesListPanel = new JPanel();
        servicesListPanel.add(servicePane);
        JPanel requestPanel = new JPanel();
        requestPanel.add(request);
        JPanel infoPanel = new JPanel();
        infoPanel.add(info);
        JPanel backPanel = new JPanel();
        back.addActionListener(new BackToCleaningMenuListener(mainFrame));
        backPanel.add(back);
        add(equipmentLabelPanel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(passportLabelPanel, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(equipmentFieldPanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(passportFieldPanel, new GridBagConstraints(2, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(servicesListPanel, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(servicesLabelPanel, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.SOUTH,
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
