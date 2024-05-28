package model;

public class ClientReservation {
    private String clientName;
    private String clientContact;
    private VehicleType vehicleType;
    private ParkingSpace parkingSpace;
    private String durationHours;

    public ClientReservation(String clientName, String clientContact, VehicleType vehicleType, String durationHours) {
        this.clientName = clientName;
        this.clientContact = clientContact;
        this.vehicleType = vehicleType;
        this.durationHours = durationHours;
    }
    
    public ClientReservation(){
    
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientContact() {
        return clientContact;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public String getDurationHours() {
        return durationHours;
    }

    public void setParkingSpace(ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
    }
}
