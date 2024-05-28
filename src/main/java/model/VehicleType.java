package model;

public enum VehicleType {
    CAR("Car", 20.0, 15.0),
    MOTORCYCLE("Motorcycle", 10.0, 7.5);

    private final String displayName;
    private final double dayPrice;
    private final double nightPrice;

    VehicleType(String displayName, double dayPrice, double nightPrice) {
        this.displayName = displayName;
        this.dayPrice = dayPrice;
        this.nightPrice = nightPrice;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getDayPrice() {
        return dayPrice;
    }

    public double getNightPrice() {
        return nightPrice;
    }
    
}

