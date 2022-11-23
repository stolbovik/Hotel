package org.stolbovik.database.hotel.GUI;

import org.stolbovik.database.hotel.GUI.Listeners.ClosedFrameListener;
import org.stolbovik.database.hotel.GUI.Panels.Admins.*;
import org.stolbovik.database.hotel.GUI.Panels.Authorization.AuthorizationPanel;
import org.stolbovik.database.hotel.GUI.Panels.Cleanings.AssignEmployeePanel;
import org.stolbovik.database.hotel.GUI.Panels.Cleanings.CleaningManagerMenuPanel;
import org.stolbovik.database.hotel.GUI.Panels.Cleanings.EndRequestPanel;
import org.stolbovik.database.hotel.GUI.Panels.Cleanings.FreeEquipmentPanel;
import org.stolbovik.database.hotel.GUI.Panels.Finances.CalculateProfitPanel;
import org.stolbovik.database.hotel.GUI.Panels.Finances.CalculateSalariesPanel;
import org.stolbovik.database.hotel.GUI.Panels.Finances.ChangePrizePanel;
import org.stolbovik.database.hotel.GUI.Panels.Finances.FinanceMenuPanel;
import org.stolbovik.database.hotel.GUI.Panels.Guests.*;
import org.stolbovik.database.hotel.utils.Constatns;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final AuthorizationPanel authorizationPanel = new AuthorizationPanel(this);
    private final GuestMenuPanel guestMenuPanel = new GuestMenuPanel(this);
    private final AdminMenuPanel adminMenuPanel = new AdminMenuPanel(this);
    private final CleaningManagerMenuPanel cleaningManagerMenuPanel = new CleaningManagerMenuPanel(this);
    private final FinanceMenuPanel financeMenuPanel = new FinanceMenuPanel(this);
    private final BookRoomPanel bookRoomPanel = new BookRoomPanel(this);
    private final CheckIntoRoomPanel checkIntoRoomPanel = new CheckIntoRoomPanel(this);
    private final CheckOutRoomPanel checkOutRoomPanel = new CheckOutRoomPanel(this);
    private final ExtendRoomPanel extendRoomPanel = new ExtendRoomPanel(this);
    private final AcceptComplaintPanel acceptComplaintPanel = new AcceptComplaintPanel(this);
    private final CheckInGuestPanel checkInGuestPanel = new CheckInGuestPanel(this);
    private final AcceptEmployeePanel acceptEmployeePanel = new AcceptEmployeePanel(this);
    private final FireEmployeePanel fireEmployeePanel = new FireEmployeePanel(this);
    private final AssignEmployeePanel assignEmployeePanel = new AssignEmployeePanel(this);
    private final EndRequestPanel endRequestPanel = new EndRequestPanel(this);
    private final FreeEquipmentPanel freeEquipmentPanel = new FreeEquipmentPanel(this);
    private final CalculateSalariesPanel calculateSalariesPanel = new CalculateSalariesPanel(this);
    private final CalculateProfitPanel calculateProfitPanel = new CalculateProfitPanel(this);
    private final ChangePrizePanel changePrizePanel = new ChangePrizePanel(this);

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
        add(bookRoomPanel);
        add(checkIntoRoomPanel);
        add(checkOutRoomPanel);
        add(extendRoomPanel);
        add(acceptComplaintPanel);
        add(checkInGuestPanel);
        add(acceptEmployeePanel);
        add(fireEmployeePanel);
        add(assignEmployeePanel);
        add(endRequestPanel);
        add(freeEquipmentPanel);
        add(calculateSalariesPanel);
        add(calculateProfitPanel);
        add(changePrizePanel);
    }

    public void setAuthorization(){
        guestMenuPanel.setVisible(false);
        adminMenuPanel.setVisible(false);
        cleaningManagerMenuPanel.setVisible(false);
        financeMenuPanel.setVisible(false);
        bookRoomPanel.setVisible(false);
        checkIntoRoomPanel.setVisible(false);
        checkOutRoomPanel.setVisible(false);
        extendRoomPanel.setVisible(false);
        acceptComplaintPanel.setVisible(false);
        checkInGuestPanel.setVisible(false);
        acceptEmployeePanel.setVisible(false);
        fireEmployeePanel.setVisible(false);
        assignEmployeePanel.setVisible(false);
        endRequestPanel.setVisible(false);
        freeEquipmentPanel.setVisible(false);
        calculateSalariesPanel.setVisible(false);
        calculateProfitPanel.setVisible(false);
        changePrizePanel.setVisible(false);
        authorizationPanel.setVisible(true);
        authorizationPanel.setInfoLabel("");
        pack();
    }
    public void setGuestMenu(){
        authorizationPanel.setVisible(false);
        bookRoomPanel.setVisible(false);
        checkIntoRoomPanel.setVisible(false);
        checkOutRoomPanel.setVisible(false);
        extendRoomPanel.setVisible(false);
        guestMenuPanel.setVisible(true);
    }

    public void setAdminMenu(){
        authorizationPanel.setVisible(false);
        acceptComplaintPanel.setVisible(false);
        checkInGuestPanel.setVisible(false);
        acceptEmployeePanel.setVisible(false);
        fireEmployeePanel.setVisible(false);
        adminMenuPanel.setVisible(true);
    }

    public void setCleaningMenu(){
        authorizationPanel.setVisible(false);
        assignEmployeePanel.setVisible(false);
        endRequestPanel.setVisible(false);
        freeEquipmentPanel.setVisible(false);
        cleaningManagerMenuPanel.setVisible(true);
    }

    public void setFinanceMenu(){
        authorizationPanel.setVisible(false);
        calculateSalariesPanel.setVisible(false);
        calculateProfitPanel.setVisible(false);
        changePrizePanel.setVisible(false);
        financeMenuPanel.setVisible(true);
    }

    public void setBookRoomPanel() {
        guestMenuPanel.setVisible(false);
        bookRoomPanel.setVisible(true);
        bookRoomPanel.setInfoLabel("");
    }

    public void setCheckIntoRoomPanel() {
        guestMenuPanel.setVisible(false);
        checkIntoRoomPanel.setVisible(true);

    }

    public void setCheckOutRoomPanel() {
        guestMenuPanel.setVisible(false);
        checkOutRoomPanel.setVisible(true);
        checkOutRoomPanel.setInfoLabel("");
    }

    public void setExtendRoomPanel() {
        guestMenuPanel.setVisible(false);
        extendRoomPanel.setVisible(true);
        extendRoomPanel.setInfoLabel("");
    }

    public void setAcceptComplaintPanel() {
        adminMenuPanel.setVisible(false);
        acceptComplaintPanel.setVisible(true);
        acceptComplaintPanel.setInfoLabel("");
    }

    public void setCheckInGuestPanel() {
        adminMenuPanel.setVisible(false);
        checkInGuestPanel.setVisible(true);
        checkInGuestPanel.setInfoLabel("");
    }

    public void setAcceptEmployeePanel() {
        adminMenuPanel.setVisible(false);
        acceptEmployeePanel.setVisible(true);
        acceptEmployeePanel.setInfoLabel("");
    }

    public void setFireEmployeePanel() {
        adminMenuPanel.setVisible(false);
        fireEmployeePanel.setVisible(true);
        fireEmployeePanel.setInfoLabel("");
    }

    public void setAssignEmployeePanel() {
        cleaningManagerMenuPanel.setVisible(false);
        assignEmployeePanel.setVisible(true);
        assignEmployeePanel.setInfoLabel("");
    }

    public void setEndRequestPanel() {
        cleaningManagerMenuPanel.setVisible(false);
        endRequestPanel.setVisible(true);
        endRequestPanel.setInfoLabel("");
    }

    public void setFreeEquipmentPanel() {
        cleaningManagerMenuPanel.setVisible(false);
        freeEquipmentPanel.setVisible(true);
        freeEquipmentPanel.setInfoLabel("");
    }

    public void setCalculateSalariesPanel() {
        financeMenuPanel.setVisible(false);
        calculateSalariesPanel.setVisible(true);
        calculateSalariesPanel.setInfoLabel("");
    }

    public void setCalculateProfitPanel() {
        financeMenuPanel.setVisible(false);
        calculateProfitPanel.setVisible(true);
        calculateProfitPanel.setInfoLabel("");
    }

    public void setChangePrizePanel() {
        financeMenuPanel.setVisible(false);
        changePrizePanel.setVisible(true);
        changePrizePanel.setInfoLabel("");
    }

}
