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
        // Implement the logic for user login
        // If login successful, set loggedInUser
        return false;
    }

    public void showMenu() {
        // Implement the logic to show the menu
    }

    public void generateReport() {
        // Implement the logic to generate a report
    }

    public void logout() {
        this.loggedInUser = null;
        // Implement the logic for user logout
    }

    public class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Implement the logic for user login
            String username = frmMenu.getUsername();
            String password = frmMenu.getPassword();
            if (login(username, password)) {
                showMenu();
            } else {
                // Show login error
            }
        }
    }

    public class LogoutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            logout();
            // Show login screen
        }
    }
}
