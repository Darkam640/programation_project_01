package controller;

import model.User;
import view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import model.ClientReservation;

public class MenuManager {

    private User loggedInUser;
    private FRM_Menu frmMenu;
    private FRM_Client frmClient;
    private FRM_Report frmReport;
    private ReservationManager register;
    private ClientReservation client;
    private ReportManager reportManager;

    public MenuManager(FRM_Menu frmMenu) {
        this.frmMenu = frmMenu;
        this.frmReport = frmReport;
        initializeListeners();
    }

    private void initializeListeners() {
        this.frmMenu.addLoginListener(new LoginListener());
        this.frmMenu.addLogoutListener(new LogoutListener());
        this.frmMenu.addReportesListener(new ReportsListener());
        this.frmMenu.addReservacionesListener(new ReservationsListener());
    }

    public boolean login(String username, String password) {
        if (isInputValid(username, password)) {
            if (authenticate(username, password)) {
                loggedInUser = new User(username, password);
                return true;
            } else {
                showErrorMessage("Invalid credentials. Please try again.");
                return false;
            }
        } else {
            showErrorMessage("Username or password cannot be empty.");
            return false;
        }
    }

    public void showReport() {
        this.register = new ReservationManager(null, null);
        this.reportManager = new ReportManager(frmReport, register);
        reportManager.showReport();
    }

    private boolean isInputValid(String username, String password) {
        return username != null && !username.trim().isEmpty() && password != null && !password.trim().isEmpty();
    }

    private boolean authenticate(String username, String password) {
        // Authentication logic (placeholder)
        return username.equals("admin") && password.equals("admin");
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }



    public void showMainMenu() {
        frmMenu.esconderLogin();
        frmMenu.mostrarMenu();
        frmClient.setVisible(false);
        frmMenu.setVisible(true);
        frmClient.limpiar();
    }

    public void logout() {
        this.loggedInUser = null;
        frmMenu.mostrarLogin();
        frmMenu.esconderMenu();
        frmMenu.limpiar();
    }

    public class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = frmMenu.getUsername();
            String password = frmMenu.getPassword();
            if (login(username, password)) {
                showMainMenu();
            } else {
                // Mostrar error de inicio de sesión
                JOptionPane.showMessageDialog(null, "failed. Please try again.");
            }
        }
    }

    private void showClientForm() {
        if (frmClient == null) {
            frmClient = new FRM_Client();
            new ReservationManager(frmClient, this);
        }
        frmMenu.setVisible(false);
        frmClient.setVisible(true);
    }

    public class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            logout();
        }
    }

    public class ReportsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            showReport();
        }
    }


    public class ReservationsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            showClientForm();
        }

    }
}
