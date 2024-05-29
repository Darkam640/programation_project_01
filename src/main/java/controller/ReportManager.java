package controller;

import java.util.List;
import model.ClientReservation;
import view.FRM_Report;
import controller.ReservationManager;

public class ReportManager {

    private FRM_Report frmReport;
    private ReservationManager register;

    public ReportManager(FRM_Report frmReport, ReservationManager reservationManager) {
        this.frmReport = frmReport;
        this.register = reservationManager;
    }

    public void showReport() {
        String[][] data = register.matrizReservaciones();
        frmReport.setDataTable(data, ClientReservation.CLIENT_TITLE);
        frmReport.setVisible(true);
    }

}
