package controller;

public class ReservationDetailsDTO {
    private String clientName;
    private String clientContact;
    private String vehicleType;
    private String parkingSpaceSide;
    private String durationHours;
    private double totalPrice;

    public ReservationDetailsDTO(String clientName, String clientContact, String vehicleType, String parkingSpaceSide, String durationHours, double totalPrice) {
        this.clientName = clientName;
        this.clientContact = clientContact;
        this.vehicleType = vehicleType;
        this.parkingSpaceSide = parkingSpaceSide;
        this.durationHours = durationHours;
        this.totalPrice = totalPrice;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientContact() {
        return clientContact;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getParkingSpaceSide() {
        return parkingSpaceSide;
    }

    public String getDurationHours() {
        return durationHours;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
