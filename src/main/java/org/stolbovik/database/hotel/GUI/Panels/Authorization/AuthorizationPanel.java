package org.stolbovik.database.hotel.GUI.Panels.Authorization;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.GUI.Listeners.AuthorizationListeners.LoggingButtonActionListener;
import org.stolbovik.database.hotel.GUI.MainFrame;
import org.stolbovik.database.hotel.utils.Constatns;

import javax.swing.*;
import java.awt.*;

public class AuthorizationPanel extends JPanel {

    private final JLabel title = new JLabel("Вход в систему отеля");
    private final JLabel loginTitle = new JLabel("Логин");
    private final JLabel passwordTitle = new JLabel("Пароль");
    private final JTextField login = new JTextField(20);
    private final JPasswordField password = new JPasswordField(20);
    private final JButton logging = new JButton("Войти");
    private final JLabel info = (new JLabel(" "));
    private final MainFrame mainFrame;

    public AuthorizationPanel(@NotNull MainFrame mainFrame) {
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
        JPanel titlePanel = new JPanel();
        titlePanel.add(title);
        JPanel loginTitlePanel = new JPanel();
        loginTitlePanel.add(loginTitle);
        JPanel passwordTitlePanel = new JPanel();
        passwordTitlePanel.add(passwordTitle);
        JPanel loginPanel = new JPanel();
        loginPanel.add(login);
        JPanel passwordPanel = new JPanel();
        passwordPanel.add(password);
        JPanel loggingPanel = new JPanel();
        logging.addActionListener(new LoggingButtonActionListener(login, password, info, mainFrame));
        loggingPanel.add(logging);
        JPanel infoPanel = new JPanel();
        infoPanel.add(info);
        add(titlePanel, new GridBagConstraints(0, 0, 1, 1, 1, 1,GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(loginTitlePanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(loginPanel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(passwordTitlePanel, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(passwordPanel, new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(infoPanel, new GridBagConstraints(0, 5, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
        add(loggingPanel, new GridBagConstraints(0, 6, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0, 0));
    }

    public void setInfoLabel(String text) {
        info.setText(text);
    }

}
