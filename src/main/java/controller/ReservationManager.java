package controller;

import model.ClientReservation;
import view.ReservationDataPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ReservationManager {
    private List<ClientReservation> activeReservations;
    private List<ClientReservation> cancelledReservations;
    private ReservationDataPanel reservationDataPanel;

    public ReservationManager(ReservationDataPanel reservationDataPanel) {
        this.activeReservations = new ArrayList<>();
        this.cancelledReservations = new ArrayList<>();
        this.reservationDataPanel = reservationDataPanel;
        this.reservationDataPanel.addAddReservationListener(new AddReservationListener());
        this.reservationDataPanel.addCancelReservationListener(new CancelReservationListener());
    }

    public void addReservation(ClientReservation reservation) {
        activeReservations.add(reservation);
        // Logic to update the view
    }

    public void cancelReservation(ClientReservation reservation) {
        activeReservations.remove(reservation);
        cancelledReservations.add(reservation);
        // Logic to update the view
    }

    public List<ClientReservation> getActiveReservations() {
        return activeReservations;
    }

    public List<ClientReservation> getCancelledReservations() {
        return cancelledReservations;
    }

    public List<ClientReservation> getDailyReservations() {
        return activeReservations;
    }

    private class AddReservationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Implement the logic to add a reservation
            // Extract data from view and create a ClientReservation
            // Call addReservation method
        }
    }

    private class CancelReservationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Implement the logic to cancel a reservation
            // Extract data from view and find the corresponding reservation
            // Call cancelReservation method
        }
    }
}
