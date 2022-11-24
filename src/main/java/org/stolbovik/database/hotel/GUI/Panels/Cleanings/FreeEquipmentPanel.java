package org.stolbovik.database.hotel.GUI.Panels.Cleanings;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.GUI.Listeners.CleaningListeners.BackToCleaningMenuListener;
import org.stolbovik.database.hotel.GUI.Listeners.CleaningListeners.ReleazeEmployeeFromEquipmentListener;
import org.stolbovik.database.hotel.GUI.MainFrame;
import org.stolbovik.database.hotel.utils.Constatns;

import javax.swing.*;
import java.awt.*;

public class FreeEquipmentPanel extends JPanel{

    private final JLabel passportLabel = new JLabel("Паспортные данные");
    private final JPasswordField passportField = new JPasswordField(20);
    private final JButton request = new JButton("Освободить от оборудования");
    private final JButton back = new JButton("Назад");
    private final JLabel info = (new JLabel(""));
    private final MainFrame mainFrame;

    public FreeEquipmentPanel(@NotNull MainFrame mainFrame) {
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
        JPanel passportFieldPanel = new JPanel();
        passportFieldPanel.add(passportField);
        JPanel requestPanel = new JPanel();
        request.addActionListener(new ReleazeEmployeeFromEquipmentListener(passportField, info));
        requestPanel.add(request);
        JPanel infoPanel = new JPanel();
        infoPanel.add(info);
        JPanel backPanel = new JPanel();
        back.addActionListener(new BackToCleaningMenuListener(mainFrame));
        backPanel.add(back);
        add(passportLabelPanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(passportFieldPanel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(infoPanel, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(requestPanel, new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(backPanel, new GridBagConstraints(0, 5, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
    }

    public void setInfoLabel(String text) {
        info.setText(text);
    }

}
