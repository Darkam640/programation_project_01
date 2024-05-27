package controller;

import controller.MenuManager;
import controller.ReservationManager;
import view.FRM_Client;
import view.FRM_Menu;

public class Main {

    public static void main(String[] args) {
        // Crear instancias de FRM_Client y FRM_Menu
        FRM_Client frmClient = new FRM_Client();
        FRM_Menu frmMenu = new FRM_Menu();

        // Crear instancias de MenuManager y ReservationManager
        MenuManager menuManager = new MenuManager(frmMenu);
        ReservationManager reservationManager = new ReservationManager(frmClient, menuManager);

        // Mostrar el formulario de clientes
        frmMenu.setVisible(true);
    }
}
