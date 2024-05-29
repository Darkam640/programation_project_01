package model;

public class ParkingSpace {
    private int id;
    private String side; // Puede ser "norte", "sur", "este", o "oeste"
    private boolean occupied; // Indica si el espacio está ocupado o disponible

    // Constructor
    public ParkingSpace(int id, String side, boolean occupied) {
        this.id = id;
        this.side = side;
        this.occupied = occupied;
    }

    public ParkingSpace() {
    }
    
    

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

}
