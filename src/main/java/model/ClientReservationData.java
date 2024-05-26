package model;

public class ClientReservationData {
    private Client client;
    private ClientReservation reservation;

    public ClientReservationData(Client client, ClientReservation reservation) {
        this.client = client;
        this.reservation = reservation;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ClientReservation getReservation() {
        return reservation;
    }

    public void setReservation(ClientReservation reservation) {
        this.reservation = reservation;
    }
}
