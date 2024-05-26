package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.ClientReservation;
import model.ParkingSpace;
import view.ReservationDataPanel;

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
    private ReservationDataPanel reservationDataPanel;

    private static final String ACTIVE_RESERVATIONS_JSON_FILE_PATH = "active_reservations.json";
    private static final String CANCELLED_RESERVATIONS_JSON_FILE_PATH = "cancelled_reservations.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ReservationManager(ReservationDataPanel reservationDataPanel) {
        this.activeReservations = loadReservationsFromJSON(ACTIVE_RESERVATIONS_JSON_FILE_PATH);
        this.cancelledReservations = loadReservationsFromJSON(CANCELLED_RESERVATIONS_JSON_FILE_PATH);
        this.northParkingSpaces = initializeParkingSpaces("north");
        this.southParkingSpaces = initializeParkingSpaces("south");
        this.eastParkingSpaces = initializeParkingSpaces("east");
        this.westParkingSpaces = initializeParkingSpaces("west");
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
        for (ParkingSpace space : parkingSpaces) {
            if (!space.isOccupied()) {
                return true;
            }
        }
        return false;
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

    private List<ParkingSpace> initializeParkingSpaces(String side) {
        List<ParkingSpace> spaces = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            spaces.add(new ParkingSpace(i + 1, side, false)); // Crear 12 espacios de estacionamiento para cada lado, inicialmente disponibles
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
            reservationDataPanel.updateView(); // Actualizar la vista después de agregar la reserva
        } else {
            reservationDataPanel.showNoAvailableSpaceMessage(); // Mostrar mensaje de que no hay espacios disponibles
        }
    }

    public class AddReservationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String clientName = reservationDataPanel.getClientName();
            String clientContact = reservationDataPanel.getClientContact();
            String side = reservationDataPanel.getSelectedSide();

            /* Aquí se debe crear un objeto Client y un objeto VehicleType
             Ejemplo:
            Client client = new Client(clientName, clientContact);
            VehicleType vehicleType = new VehicleType(); // Esto debería ser definido correctamente
            LocalDateTime reservationTime = LocalDateTime.now();
            int duration = 2; // Esto puede ser tomado desde el formulario o ser un valor predeterminado
            TariffType tariffType = new TariffType(); // Esto debería ser definido correctamente

            ClientReservation reservation = new ClientReservation(client, vehicleType, reservationTime, duration, tariffType);
            switch (side) {
                case "north":
                    assignParkingSpace(reservation, northParkingSpaces);
                    break;
                case "south":
                    assignParkingSpace(reservation, southParkingSpaces);
                    break;
                case "east":
                    assignParkingSpace(reservation, eastParkingSpaces);
                    break;
                case "west":
                    assignParkingSpace(reservation, westParkingSpaces);
                    break;
                default:
                    reservationDataPanel.showInvalidSideMessage(); // Mostrar mensaje de que el lado seleccionado es inválido
            }
            */
        }
        
    }

    public class CancelReservationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String clientName = reservationDataPanel.getClientName();
            ClientReservation reservation = findReservationByName(clientName);
            if (reservation != null) {
                cancelReservation(reservation);
                reservation.getParkingSpace().setOccupied(false);
                reservationDataPanel.updateView(); // Actualizar la vista después de cancelar la reserva
            } else {
                reservationDataPanel.showReservationNotFoundMessage(); // Mostrar mensaje de que la reserva no fue encontrada
            }
        }
    }

    private ClientReservation findReservationByName(String clientName) {
        for (ClientReservation reservation : activeReservations) {
            if (reservation.getClientName().equals(clientName)) {
                return reservation;
            }
        }
        return null;
    }
}
