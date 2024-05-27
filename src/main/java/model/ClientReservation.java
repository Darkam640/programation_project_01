package model;

import java.time.LocalDateTime;

public class ClientReservation {

    private String clientName;
    private String clientContact;
    private VehicleType vehicleType;
    private LocalDateTime start;
    private int durationHours;
    private TariffType tariffType;
    private ParkingSpace parkingSpace;

    public ClientReservation(String clientName, String clientContact, VehicleType vehicleType, LocalDateTime start, int durationHours, TariffType tariffType) {
        this.clientName = clientName;
        this.clientContact = clientContact;
        this.vehicleType = vehicleType;
        this.start = start;
        this.durationHours = durationHours;
        this.tariffType = tariffType;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientContact() {
        return clientContact;
    }

    public void setClientContact(String clientContact) {
        this.clientContact = clientContact;
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

    public void setParkingSpace(ParkingSpace space) {
        this.parkingSpace = space;
    }

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }
}
