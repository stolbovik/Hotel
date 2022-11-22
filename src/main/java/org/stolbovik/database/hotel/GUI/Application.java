package org.stolbovik.database.hotel.GUI;

import org.stolbovik.database.hotel.utils.Constatns;

public class Application {

    public void run() {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setAuthorization();
        while (Constatns.applicationIsWork) {

        }
    }

}
