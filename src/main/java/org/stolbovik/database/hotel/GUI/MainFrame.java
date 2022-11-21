package org.stolbovik.database.hotel.GUI;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.GUI.Panels.AuthorizationPanel;
import org.stolbovik.database.hotel.utils.Constatns;

import javax.swing.*;
import java.awt.*;
import java.sql.Statement;

public class MainFrame extends JFrame {

    public MainFrame() {
        super();
        setBaseParameter();
    }

    private void setBaseParameter() {
        setSize(Constatns.WINDOW_WIDTH, Constatns.WINDOW_HEIGHT);
        setTitle("Отель");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new FlowLayout());
        setVisible(true);
    }

    public void openAuthorization(){
        add(new AuthorizationPanel());
        setVisible(true);
        pack();
    }

}
