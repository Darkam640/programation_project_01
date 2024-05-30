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

    public void initializeListeners() {
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
            ReservationDetailsDTO reservationDTO = convertToDTO(reservation);
            frmClient.showReservationDetails(reservationDTO);
        } else {
            frmClient.showReservationNotFoundMessage();
        }
    }

    private ReservationDetailsDTO convertToDTO(ClientReservation reservation) {
        String clientName = reservation.getClientName();
        String clientContact = reservation.getClientContact();
        String vehicleType = translateVehicleTypeToSpanish(reservation.getVehicleType().getDisplayName());
        String parkingSpaceSide = translateParkingSpaceSideToSpanish(reservation.getParkingSpace().getSide());
        String durationHours = reservation.getDurationHours();
        double totalPrice = calculatePrice(reservation.getVehicleType(), durationHours);
        return new ReservationDetailsDTO(clientName, clientContact, vehicleType, parkingSpaceSide, durationHours, totalPrice);
    }

    private double calculatePrice(VehicleType vehicleType, String durationHours) {
        double price = 0.0;
        switch (durationHours) {
            case "6":
                price = vehicleType.getHalfDayPrice();
                break;
            case "24":
                price = vehicleType.getDayPrice();
                break;
            case "8":
                price = vehicleType.getNightPrice();
                break;
        }
        return price;
    }

    public String translateVehicleType(String selectedItem) {
        if (selectedItem == null) {
            return null;
        }
        switch (selectedItem) {
            case "Vehiculo":
                return "CAR";
            case "Motocicleta":
                return "MOTORCYCLE";
            default:
                return null;
        }
    }

    public String translateVehicleTypeToSpanish(String vehicleType) {
        if (vehicleType == null) {
            return null;
        }
        switch (vehicleType) {
            case "Car":
                return "Vehiculo";
            case "Motorcycle":
                return "Motocicleta";
            default:
                return null;
        }
    }

    public String translateParkingSpaceSideToSpanish(String side) {
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

    public List<ClientReservation> getActiveReservations() {
        return activeReservations;
    }

    public List<ClientReservation> getCancelledReservations() {
        return cancelledReservations;
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
        String clientName = frmClient.getClientName();
        String clientContact = frmClient.getClientContact();

        if (clientName == null || clientName.isEmpty() || clientContact == null || clientContact.isEmpty()) {
            frmClient.mensaje("El nombre y el contacto del cliente no pueden estar vacíos.");
            return;
        }

        String side = frmClient.getSelectedSide();
        String translatedVehicleType = translateVehicleType(frmClient.getSelectedItemVehiculo());

        if (translatedVehicleType == null) {
            frmClient.showInvalidSideMessage();
            return;
        }

        String durationHours = frmClient.getSelectedItemHorario().equals("Medio dia") ? "6" :
                (frmClient.getSelectedItemHorario().equals("Dia entero") ? "24" : "8");

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
                return;
        }

        double price = calculatePrice(VehicleType.valueOf(translatedVehicleType), durationHours);
        frmClient.setTotalPrice(price);
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

    private void handleSearchReservation() {
        String clientContact = frmClient.getClientContact();
        searchReservation(clientContact);
    }

    private void handleBackMenu() {
        menuManager.showMainMenu();
    }

    public String[][] matrizReservaciones() {
        List<ClientReservation> reservacionesActivas = loadReservationsFromJSON(ACTIVE_RESERVATIONS_JSON_FILE_PATH);

        String[][] matriz = new String[reservacionesActivas.size()][5];

        for (int i = 0; i < reservacionesActivas.size(); i++) {
            ClientReservation reserva = reservacionesActivas.get(i);
            matriz[i][0] = reserva.getClientName();
            matriz[i][1] = reserva.getClientContact();
            matriz[i][2] = reserva.getParkingSpace().getSide();
            matriz[i][3] = reserva.getVehicleType().toString();
            matriz[i][4] = reserva.getDurationHours();
        }

        return matriz;
    }

}
