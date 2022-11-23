package org.stolbovik.database.hotel.GUI.Panels.Cleanings;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.GUI.Listeners.AdminListeners.BackToAdminMenuListener;
import org.stolbovik.database.hotel.GUI.Listeners.CleaningListeners.BackToCleaningMenuListener;
import org.stolbovik.database.hotel.GUI.MainFrame;
import org.stolbovik.database.hotel.utils.Constatns;

import javax.swing.*;
import java.awt.*;

public class AssignEmployeePanel extends JPanel {

    private final JLabel requestLabel = new JLabel("Номер заявки");
    private final JTextField requestField = new JTextField(20);
    private final JButton request = new JButton("Назначить");
    private final JButton back = new JButton("Назад");
    private final JLabel info = (new JLabel(""));
    private final MainFrame mainFrame;

    public AssignEmployeePanel(@NotNull MainFrame mainFrame) {
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
        JPanel requestLabelPanel = new JPanel();
        requestLabelPanel.add(requestLabel);
        JPanel requestFieldPanel = new JPanel();
        requestFieldPanel.add(requestField);
        JPanel requestPanel = new JPanel();
        requestPanel.add(request);
        JPanel infoPanel = new JPanel();
        infoPanel.add(info);
        JPanel backPanel = new JPanel();
        back.addActionListener(new BackToCleaningMenuListener(mainFrame));
        backPanel.add(back);
        add(requestLabelPanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(requestFieldPanel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.SOUTH,
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
