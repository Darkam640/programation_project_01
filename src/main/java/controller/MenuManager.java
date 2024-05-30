package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.*;
import view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MenuManager {

    private User loggedInUser;
    private FRM_Menu frmMenu;
    private FRM_Client frmClient;

    private static final String ACTIVE_RESERVATIONS_JSON_FILE_PATH = "src/main/resources/active_reservations.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MenuManager(FRM_Menu frmMenu) {
        this.frmMenu = frmMenu;
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
        try {
            List<ClientReservation> reservations = loadReservationsFromJSON(ACTIVE_RESERVATIONS_JSON_FILE_PATH);
            FRM_Report reportFrame = new FRM_Report(reservations);
            reportFrame.setVisible(true);
        } catch (IOException e) {
            showErrorMessage("Error loading reservations: " + e.getMessage());
        }
    }
    
    private List<ClientReservation> loadReservationsFromJSON(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            return objectMapper.readValue(file, new TypeReference<List<ClientReservation>>() {});
        }
        return List.of(); // Return an empty list if the file does not exist
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
        }
    }

    public class ReservationsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            showClientForm();
        }
    }
}