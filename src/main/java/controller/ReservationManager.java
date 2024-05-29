package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.*;
import view.FRM_Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
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
    private FRM_Client frmClient;
    private MenuManager menuManager;

    private static final String ACTIVE_RESERVATIONS_JSON_FILE_PATH = "src/main/resources/active_reservations.json";
    private static final String CANCELLED_RESERVATIONS_JSON_FILE_PATH = "src/main/resources/cancelled_reservations.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ReservationManager(FRM_Client frmClient, MenuManager menuManager) {
        this.activeReservations = loadReservationsFromJSON(ACTIVE_RESERVATIONS_JSON_FILE_PATH);
        this.cancelledReservations = loadReservationsFromJSON(CANCELLED_RESERVATIONS_JSON_FILE_PATH);
        this.northParkingSpaces = initializeParkingSpaces("north");
        this.southParkingSpaces = initializeParkingSpaces("south");
        this.eastParkingSpaces = initializeParkingSpaces("east");
        this.westParkingSpaces = initializeParkingSpaces("west");
        this.frmClient = frmClient;
        this.menuManager = menuManager;
        initializeListeners();
    }

    private void initializeListeners() {
        this.frmClient.addAddReservationListener(new AddReservationListener());
        this.frmClient.addCancelReservationListener(new CancelReservationListener());
        this.frmClient.addSearchReservationListener(new SearchReservationListener());
        this.frmClient.addBackButtonListener(new BackMenuListener());
    }

    public void addReservation(ClientReservation reservation) {
        String clientContact = reservation.getClientContact();
        if (findReservationByContact(clientContact) == null) {
            activeReservations.add(reservation);
            saveReservationsToJSON(activeReservations, ACTIVE_RESERVATIONS_JSON_FILE_PATH);
            frmClient.updateView();
        } else {
            frmClient.showReservationExistsMessage();
        }
    }

    public void cancelReservation(ClientReservation reservation) {
        activeReservations.remove(reservation);
        cancelledReservations.add(reservation);
        saveReservationsToJSON(activeReservations, ACTIVE_RESERVATIONS_JSON_FILE_PATH);
        saveReservationsToJSON(cancelledReservations, CANCELLED_RESERVATIONS_JSON_FILE_PATH);
        reservation.getParkingSpace().setOccupied(false);
        frmClient.limpiar();
    }

    public void searchReservation(String clientContact) {
        ClientReservation reservation = findReservationByContact(clientContact);
        if (reservation != null) {
            frmClient.showReservationDetails(reservation.getClientName(), reservation.getClientContact(),
                    reservation.getParkingSpace().getSide(), reservation.getVehicleType().toString(),
                    reservation.getDurationHours());
        } else {
            frmClient.showReservationNotFoundMessage();
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
            ParkingSpace space = availableSpaces.get(random.nextInt(availableSpaces.size()));
            reservation.setParkingSpace(space);
            space.setOccupied(true);
            addReservation(reservation);
        } else {
            frmClient.showNoAvailableSpaceMessage();
        }
    }

    private ClientReservation findReservationByContact(String clientContact) {
        return activeReservations.stream()
                .filter(reservation -> reservation.getClientContact().equals(clientContact))
                .findFirst()
                .orElse(null);
    }

    private void handleAddReservation() {
        String clientName = frmClient.getClientName();
        String clientContact = frmClient.getClientContact();
        String side = frmClient.getSelectedSide();

        String translatedVehicleType = frmClient.translateVehicleType(frmClient.getSelectedItemVehiculo());

        String durationHours = frmClient.getSelectedItemHorario().equals("Medio dia") ? "6" : (frmClient.getSelectedItemHorario().equals("Dia entero") ? "24" : "8");

        ClientReservation reservation = new ClientReservation(clientName, clientContact, VehicleType.valueOf(translatedVehicleType), durationHours);

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
                frmClient.showInvalidSideMessage();
                break;
        }
    }

    private void handleCancelReservation() {
        String clientContact = frmClient.getClientContact();
        ClientReservation reservation = findReservationByContact(clientContact);
        if (reservation != null) {
            cancelReservation(reservation);
        } else {
            frmClient.showReservationNotFoundMessage();
        }
    }

    private void handleBackMenu() {
        menuManager.showMainMenu();
    }

    private void handleSearchReservation() {
        String clientContact = frmClient.getClientContact();
        searchReservation(clientContact);
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
    
        public String[][] matrizReservaciones() {
            String[][] matrizReservaciones = new String[this.activeReservations.size()][ClientReservation.CLIENT_TITLE.length];

       
        for (int f = 0; f < matrizReservaciones.length; f++) {
            matrizReservaciones[f][0] = activeReservations.get(f).getClientName();
            matrizReservaciones[f][1] = activeReservations.get(f).getClientContact()+ "";
            matrizReservaciones[f][2] = activeReservations.get(f).getVehicleType().toString();
            matrizReservaciones[f][3] = activeReservations.get(f).getDurationHours();        
           
        }
        return matrizReservaciones;
    }
}
