package model;

import java.time.LocalDateTime;
import java.util.*;

public class ClientReservation {
    private Client client;
    private VehicleType vehicleType;
    private LocalDateTime start;
    private int durationHours;
    private TariffType tariffType;

    public ClientReservation(Client client, VehicleType vehicleType, LocalDateTime start, int durationHours, TariffType tariffType) {
        this.client = client;
        this.vehicleType = vehicleType;
        this.start = start;
        this.durationHours = durationHours;
        this.tariffType = tariffType;
    }

    public boolean addReservation() {
        // Implement the logic to add a reservation
        return true;
    }

    public boolean checkAvailability() {
        // Implement the logic to check availability
        return true;
    }

    public boolean removeReservation() {
        // Implement the logic to remove a reservation
        return true;
    }

    public List<ClientReservation> getReservations() {
        // Implement the logic to get reservations
        return new ArrayList<>();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public int getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(int durationHours) {
        this.durationHours = durationHours;
    }

    public TariffType getTariffType() {
        return tariffType;
    }

    public void setTariffType(TariffType tariffType) {
        this.tariffType = tariffType;
    }
}
