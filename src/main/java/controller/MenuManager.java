package controller;

import model.User;
import view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.ClientReservation;

public class MenuManager {

    private User loggedInUser;
    private FRM_Menu frmMenu;
    private FRM_Client frmClient;
    private FRM_Report frmReport;
    private ReservationManager register;
     private ClientReservation client;

    public MenuManager(FRM_Menu frmMenu) {
        this.frmMenu = frmMenu;
        initializeListeners();
        this.frmReport = new FRM_Report();
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

    public void generateReport() {
        // Implement the logic to generate a report
    }

    public void showMainMenu() {
        frmMenu.esconderLogin();
        frmMenu.mostrarMenu();
        if (frmClient != null) {
            frmClient.setVisible(false);
            frmClient.limpiar();
        }
        frmMenu.setVisible(true);
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
            generateReport();
                         // this.frmReport.setDataTable(register.matrizReservaciones(),client.CLIENT_TITLE);
               // this.frmReport.setVisible(true);     
        }
    }
    
    
    public void actionPerformed(ActionEvent e){
        switch(e.getActionCommand().toString())
        {
            case "Reportes ":
                this.frmReport.setDataTable(register.matrizReservaciones(),client.CLIENT_TITLE);
                this.frmReport.setVisible(true);     
                break;
        }
    }    

    public class ReservationsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            showClientForm();
        }
        
        
    }
}
