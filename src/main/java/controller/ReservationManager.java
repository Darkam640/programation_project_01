package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.ClientReservation;
import view.ReservationDataPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReservationManager {
    private List<ClientReservation> activeReservations;
    private List<ClientReservation> cancelledReservations;
    private ReservationDataPanel reservationDataPanel;

    private static final String ACTIVE_RESERVATIONS_JSON_FILE_PATH = "active_reservations.json";
    private static final String CANCELLED_RESERVATIONS_JSON_FILE_PATH = "cancelled_reservations.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ReservationManager(ReservationDataPanel reservationDataPanel) {
        this.activeReservations = loadReservationsFromJSON(ACTIVE_RESERVATIONS_JSON_FILE_PATH);
        this.cancelledReservations = loadReservationsFromJSON(CANCELLED_RESERVATIONS_JSON_FILE_PATH);
        this.reservationDataPanel = reservationDataPanel;
        this.reservationDataPanel.addAddReservationListener(new AddReservationListener());
        this.reservationDataPanel.addCancelReservationListener(new CancelReservationListener());
    }

    public void addReservation(ClientReservation reservation) {
        activeReservations.add(reservation);
        saveReservationsToJSON(activeReservations, ACTIVE_RESERVATIONS_JSON_FILE_PATH);
        // Logic to update the view
    }

    public void cancelReservation(ClientReservation reservation) {
        activeReservations.remove(reservation);
        cancelledReservations.add(reservation);
        saveReservationsToJSON(activeReservations, ACTIVE_RESERVATIONS_JSON_FILE_PATH);
        saveReservationsToJSON(cancelledReservations, CANCELLED_RESERVATIONS_JSON_FILE_PATH);
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

    private List<ClientReservation> loadReservationsFromJSON(String filePath) {
        List<ClientReservation> loadedReservations = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                loadedReservations = objectMapper.readValue(file, new TypeReference<List<ClientReservation>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedReservations;
    }

    private void saveReservationsToJSON(List<ClientReservation> reservations, String filePath) {
        try {
            objectMapper.writeValue(new File(filePath), reservations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class AddReservationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Implement the logic to add a reservation
            // Extract data from view and create a ClientReservation
            // Call addReservation method
        }
    }

    public class CancelReservationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Implement the logic to cancel a reservation
            // Extract data from view and find the corresponding reservation
            // Call cancelReservation method
        }
    }
}
