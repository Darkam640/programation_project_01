package model;

public enum VehicleType {
    CAR("Car", 20.0, 15.0, 10.0),
    MOTORCYCLE("Motorcycle", 10.0, 7.5, 5.0);

    private final String displayName;
    private final double dayPrice;
    private final double nightPrice;
    private final double halfDayPrice;

    VehicleType(String displayName, double dayPrice, double nightPrice, double halfDayPrice) {
        this.displayName = displayName;
        this.dayPrice = dayPrice;
        this.nightPrice = nightPrice;
        this.halfDayPrice = halfDayPrice;
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

    public double getHalfDayPrice() {
        return halfDayPrice;
    }
}
