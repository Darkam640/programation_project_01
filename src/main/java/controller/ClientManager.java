package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.*;
import view.FRM_Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClientManager {

    private List<ClientReservationData> clientReservationDataList;
    private FRM_Client frmClient;

    private static final String JSON_FILE_PATH = "client_reservation_data.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ClientManager(FRM_Client frmClient) {
        this.clientReservationDataList = loadClientReservationDataFromJSON();
        this.frmClient = frmClient;
        this.frmClient.addAddClientListener(new AddClientListener());
        this.frmClient.addRemoveClientListener(new RemoveClientListener());
        this.frmClient.addFindClientListener(new FindClientListener());
    }

    public void addClient(Client client) {
        // Create a reservation for the client
        VehicleType defaultVehicleType = VehicleType.CAR;
        LocalDateTime reservationTime = LocalDateTime.now();
        int duration = 2;
        TariffType defaultTariffType = TariffType.DAILY;
        ClientReservation reservation = new ClientReservation(client, defaultVehicleType, reservationTime, duration, defaultTariffType);
        // Create ClientReservationData object and add it to the list
        ClientReservationData clientReservationData = new ClientReservationData(client, reservation);
        clientReservationDataList.add(clientReservationData);
        saveClientReservationDataToJSON();
        // Update view if necessary
    }

    public void removeClient(Client client) {
        // Remove client and associated reservation data from the list
        clientReservationDataList.removeIf(data -> data.getClient().equals(client));
        saveClientReservationDataToJSON();
        // Update view if necessary
    }

    public Client findClient(String name) {
        for (ClientReservationData data : clientReservationDataList) {
            if (data.getClient().getName().equals(name)) {
                return data.getClient();
            }
        }
        return null;
    }

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        for (ClientReservationData data : clientReservationDataList) {
            clients.add(data.getClient());
        }
        return clients;
    }

    private List<ClientReservationData> loadClientReservationDataFromJSON() {
        List<ClientReservationData> loadedClientReservationDataList = new ArrayList<>();
        try {
            File file = new File(JSON_FILE_PATH);
            if (file.exists()) {
                loadedClientReservationDataList = objectMapper.readValue(file, new TypeReference<List<ClientReservationData>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedClientReservationDataList;
    }

    private void saveClientReservationDataToJSON() {
        try {
            objectMapper.writeValue(new File(JSON_FILE_PATH), clientReservationDataList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class AddClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = frmClient.getClientName();
            String contact = frmClient.getClientContact();
            addClient(new Client(name, contact));
            // Update view if necessary
        }
    }

    public class RemoveClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = frmClient.getClientName();
            Client client = findClient(name);
            if (client != null) {
                removeClient(client);
                // Update view if necessary
            }
        }
    }

    public class FindClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = frmClient.getClientName();
            Client client = findClient(name);
            if (client != null) {
                // Update view with client details
                frmClient.setClientContact(client.getContact());
            }
        }
    }
}
