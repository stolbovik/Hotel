package org.stolbovik.database.hotel.GUI;

import org.stolbovik.database.hotel.GUI.Listeners.ClosedFrameListener;
import org.stolbovik.database.hotel.GUI.Panels.Admins.AdminMenuPanel;
import org.stolbovik.database.hotel.GUI.Panels.Authorization.AuthorizationPanel;
import org.stolbovik.database.hotel.GUI.Panels.Cleanings.CleaningManagerMenuPanel;
import org.stolbovik.database.hotel.GUI.Panels.Finances.FinanceMenuPanel;
import org.stolbovik.database.hotel.GUI.Panels.Guests.GuestMenuPanel;
import org.stolbovik.database.hotel.utils.Constatns;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final AuthorizationPanel authorizationPanel = new AuthorizationPanel(this);
    private final GuestMenuPanel guestMenuPanel = new GuestMenuPanel(this);
    private final AdminMenuPanel adminMenuPanel = new AdminMenuPanel(this);
    private final CleaningManagerMenuPanel cleaningManagerMenuPanel = new CleaningManagerMenuPanel(this);
    private final FinanceMenuPanel financeMenuPanel = new FinanceMenuPanel(this);

    public MainFrame() {
        super();
        setBaseParameter();
    }

    private void setBaseParameter() {
        setSize(Constatns.WINDOW_WIDTH, Constatns.WINDOW_HEIGHT);
        setTitle("Отель");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new ClosedFrameListener());
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new FlowLayout());
        setVisible(true);
        add(authorizationPanel);
        add(guestMenuPanel);
        add(adminMenuPanel);
        add(cleaningManagerMenuPanel);
        add(financeMenuPanel);
    }

    public void setAuthorization(){
        guestMenuPanel.setVisible(false);
        adminMenuPanel.setVisible(false);
        cleaningManagerMenuPanel.setVisible(false);
        financeMenuPanel.setVisible(false);
        authorizationPanel.setVisible(true);
        authorizationPanel.setInfoLabel("");
        pack();
    }
    public void setGuestMenu(){
        authorizationPanel.setVisible(false);
        adminMenuPanel.setVisible(false);
        cleaningManagerMenuPanel.setVisible(false);
        financeMenuPanel.setVisible(false);
        guestMenuPanel.setVisible(true);
        pack();
    }

    public void setAdminMenu(){
        authorizationPanel.setVisible(false);
        guestMenuPanel.setVisible(false);
        cleaningManagerMenuPanel.setVisible(false);
        financeMenuPanel.setVisible(false);
        adminMenuPanel.setVisible(true);
        pack();
    }

    public void setCleaningMenu(){
        guestMenuPanel.setVisible(false);
        adminMenuPanel.setVisible(false);
        authorizationPanel.setVisible(false);
        financeMenuPanel.setVisible(false);
        cleaningManagerMenuPanel.setVisible(true);
        pack();
    }

    public void setFinanceMenu(){
        guestMenuPanel.setVisible(false);
        adminMenuPanel.setVisible(false);
        authorizationPanel.setVisible(false);
        cleaningManagerMenuPanel.setVisible(false);
        financeMenuPanel.setVisible(true);
        pack();
    }

}
