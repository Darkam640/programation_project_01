package controller;

import controller.MenuManager;
import controller.ReservationManager;
import view.FRM_Client;
import view.FRM_Menu;

public class Main {

    public static void main(String[] args) {

        FRM_Client frmClient = new FRM_Client();
        FRM_Menu frmMenu = new FRM_Menu();


        MenuManager menuManager = new MenuManager(frmMenu);
        ReservationManager reservationManager = new ReservationManager(frmClient, menuManager);


        frmMenu.setVisible(true);
    }
}
