package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.*;
import view.FRM_Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ReservationManager {

    private List<ClientReservation> activeReservations;
    private List<ClientReservation> cancelledReservations;
    private List<ParkingSpace> northParkingSpaces;
    private List<ParkingSpace> southParkingSpaces;
    private List<ParkingSpace> eastParkingSpaces;
    private List<ParkingSpace> westParkingSpaces;
    private FRM_Client frm_client;
    private MenuManager menuManager;

    private static final String ACTIVE_RESERVATIONS_JSON_FILE_PATH = "active_reservations.json";
    private static final String CANCELLED_RESERVATIONS_JSON_FILE_PATH = "cancelled_reservations.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ReservationManager(FRM_Client frm_client, MenuManager menuManager) {
        this.activeReservations = loadReservationsFromJSON(ACTIVE_RESERVATIONS_JSON_FILE_PATH);
        this.cancelledReservations = loadReservationsFromJSON(CANCELLED_RESERVATIONS_JSON_FILE_PATH);
        this.northParkingSpaces = initializeParkingSpaces("north");
        this.southParkingSpaces = initializeParkingSpaces("south");
        this.eastParkingSpaces = initializeParkingSpaces("east");
        this.westParkingSpaces = initializeParkingSpaces("west");
        this.frm_client = frm_client;
        this.menuManager = menuManager;
        this.frm_client.addAddReservationListener(new AddReservationListener());
        this.frm_client.addCancelReservationListener(new CancelReservationListener());
        this.frm_client.addSearchReservationListener(new SearchReservationListener());
        this.frm_client.addBackButtonListener(new BackMenuListener());
    }

    public void addReservation(ClientReservation reservation) {
        String clientContact = reservation.getClientContact();
        if (findReservationByContact(clientContact) == null) {
            activeReservations.add(reservation);
            saveReservationsToJSON(activeReservations, ACTIVE_RESERVATIONS_JSON_FILE_PATH);
            frm_client.updateView();
        } else {
            frm_client.showReservationExistsMessage();
        }
    }

    public void cancelReservation(ClientReservation reservation) {
        activeReservations.remove(reservation);
        cancelledReservations.add(reservation);
        saveReservationsToJSON(activeReservations, ACTIVE_RESERVATIONS_JSON_FILE_PATH);
        saveReservationsToJSON(cancelledReservations, CANCELLED_RESERVATIONS_JSON_FILE_PATH);
        frm_client.updateView();
    }

    public void searchReservation(String clientContact) {
        ClientReservation reservation = findReservationByContact(clientContact);
        if (reservation != null) {
            String clientName = reservation.getClientName();
            String selectedSide = frm_client.getSelectedSide();
            String selectedItemVehiculo = frm_client.getSelectedItemVehiculo();
            String selectedItemHorario = frm_client.getSelectedItemHorario();

            frm_client.showReservationDetails(clientName, clientContact, selectedSide, selectedItemVehiculo, selectedItemHorario);
        } else {
            frm_client.showReservationNotFoundMessage();
        }
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

    public boolean isNorthParkingSpaceAvailable() {
        return checkParkingSpaceAvailability(northParkingSpaces);
    }

    public boolean isSouthParkingSpaceAvailable() {
        return checkParkingSpaceAvailability(southParkingSpaces);
    }

    public boolean isEastParkingSpaceAvailable() {
        return checkParkingSpaceAvailability(eastParkingSpaces);
    }

    public boolean isWestParkingSpaceAvailable() {
        return checkParkingSpaceAvailability(westParkingSpaces);
    }

    private boolean checkParkingSpaceAvailability(List<ParkingSpace> parkingSpaces) {
        return parkingSpaces.stream().anyMatch(space -> !space.isOccupied());
    }

    private List<ClientReservation> loadReservationsFromJSON(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                return objectMapper.readValue(file, new TypeReference<List<ClientReservation>>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private List<ParkingSpace> initializeParkingSpaces(String side) {
        List<ParkingSpace> spaces = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            spaces.add(new ParkingSpace(i + 1, side, false));
        }
        return spaces;
    }

    private void saveReservationsToJSON(List<ClientReservation> reservations, String filePath) {
        try {
            objectMapper.writeValue(new File(filePath), reservations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void assignParkingSpace(ClientReservation reservation, List<ParkingSpace> parkingSpaces) {
        List<ParkingSpace> availableSpaces = parkingSpaces.stream()
                .filter(space -> !space.isOccupied())
                .collect(Collectors.toList());

        if (!availableSpaces.isEmpty()) {
            Random random = new Random();
            int index = random.nextInt(availableSpaces.size());
            ParkingSpace space = availableSpaces.get(index);
            reservation.setParkingSpace(space);
            space.setOccupied(true);
            addReservation(reservation);
            frm_client.updateView();
        } else {
            frm_client.showNoAvailableSpaceMessage();
        }
    }

    public class AddReservationListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            handleAddReservation();
        }
    }

    public class CancelReservationListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            handleCancelReservation();
        }
    }

    public class SearchReservationListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            handleSearchReservation();
        }
    }

    public class BackMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            handleBackMenu();
        }
    }

    private void handleAddReservation() {
        String clientName = frm_client.getClientName();
        String clientContact = frm_client.getClientContact();
        String side = frm_client.getSelectedSide();
        VehicleType vehicleType = VehicleType.valueOf(frm_client.getSelectedItemVehiculo().toUpperCase());
        LocalDateTime reservationTime = LocalDateTime.now();
        int duration = frm_client.getSelectedItemHorario().equals("Medio dia") ? 6 : (frm_client.getSelectedItemHorario().equals("Dia entero") ? 24 : 8);
        TariffType tariffType = TariffType.DAILY;

        ClientReservation reservation = new ClientReservation(clientName, clientContact, vehicleType, reservationTime, duration, tariffType);

        switch (side) {
            case "Norte":
                assignParkingSpace(reservation, northParkingSpaces);
                break;
            case "Sur":
                assignParkingSpace(reservation, southParkingSpaces);
                break;
            case "Este":
                assignParkingSpace(reservation, eastParkingSpaces);
                break;
            case "Oeste":
                assignParkingSpace(reservation, westParkingSpaces);
                break;
            default:
                frm_client.showInvalidSideMessage();
                break;
        }
    }

    private void handleCancelReservation() {
        String clientContact = frm_client.getClientContact();
        ClientReservation reservation = findReservationByContact(clientContact);
        if (reservation != null) {
            cancelReservation(reservation);
            reservation.getParkingSpace().setOccupied(false);
            frm_client.updateView();
        } else {
            frm_client.showReservationNotFoundMessage();
        }
    }

    private void handleBackMenu() {
        menuManager.showMainMenu();
    }

    private void handleSearchReservation() {
        String clientContact = frm_client.getClientContact();
        searchReservation(clientContact);
    }

    private ClientReservation findReservationByContact(String clientContact) {
        for (ClientReservation reservation : activeReservations) {
            if (reservation.getClientContact().equals(clientContact)) {
                return reservation;
            }
        }
        return null;
    }
}
