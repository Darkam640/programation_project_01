package controller;

import model.User;
import view.FRM_Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuManager {

    private User loggedInUser;
    private FRM_Menu frmMenu;

    public MenuManager(FRM_Menu frmMenu) {
        this.frmMenu = frmMenu;
        this.frmMenu.addLoginListener(new LoginListener());
        this.frmMenu.addLogoutListener(new LogoutListener());
    }

    public boolean login(String username, String password) {
        // Aquí implementamos la lógica de inicio de sesión, por ahora es solo un ejemplo
        if (username.equals("admin") && password.equals("admin")) {
            loggedInUser = new User(username, password);
            return true;
        }
        return false;
    }

    public void generateReport() {
        // Implement the logic to generate a report
    }

    public void showMainMenu() {
        frmMenu.esconderLogin();
        frmMenu.mostrarMenu();
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
                System.out.println("Login failed. Please try again.");
            }
        }
    }

    public class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            logout();
        }
    }
}
