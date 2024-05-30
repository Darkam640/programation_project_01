package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import model.ClientReservation;

public class FRM_Report extends JFrame {

    private JTable reportTable;
    private DefaultTableModel tableModel;

    public FRM_Report(List<ClientReservation> reservations) {
        setTitle("Reporte de Reservaciones");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnNames = {"Nombre", "Lado", "Contacto", "Horario de Estacionamiento"};
        tableModel = new DefaultTableModel(columnNames, 0);
        reportTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(reportTable);
        add(scrollPane, BorderLayout.CENTER);

        // Populate the table with reservation data
        populateTable(reservations);
    }

    private void populateTable(List<ClientReservation> reservations) {
        for (ClientReservation reservation : reservations) {
            String nombre = reservation.getClientName();
            String lado = translateParkingSpaceSideToSpanish(reservation.getParkingSpace().getSide());
            String contacto = reservation.getClientContact();
            String horario = translateDurationToSpanish(reservation.getDurationHours());
            Object[] row = {nombre, lado, contacto, horario};
            tableModel.addRow(row);
        }
    }

    private String translateParkingSpaceSideToSpanish(String side) {
        if (side == null) {
            return null;
        }
        switch (side) {
            case "north":
                return "Norte";
            case "south":
                return "Sur";
            case "east":
                return "Este";
            case "west":
                return "Oeste";
            default:
                return null;
        }
    }

    private String translateDurationToSpanish(String durationHours) {
        switch (durationHours) {
            case "6":
                return "Medio dia";
            case "24":
                return "Dia entero";
            case "8":
                return "Noche";
            default:
                return null;
        }
    }
}
