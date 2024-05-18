package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Client;
import view.FRM_Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientManager {
    private List<Client> clients;
    private FRM_Client frmClient;

    private static final String JSON_FILE_PATH = "clients.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ClientManager(FRM_Client frmClient) {
        this.clients = loadClientsFromJSON(); // Load clients from JSON file
        this.frmClient = frmClient;
        this.frmClient.addAddClientListener(new AddClientListener());
        this.frmClient.addRemoveClientListener(new RemoveClientListener());
        this.frmClient.addFindClientListener(new FindClientListener());
    }

    public void addClient(Client client) {
        clients.add(client);
        saveClientsToJSON(); // Save clients to JSON file after adding
        // Logic to update the view
    }

    public void removeClient(Client client) {
        clients.remove(client);
        saveClientsToJSON(); // Save clients to JSON file after removing
        // Logic to update the view
    }

    public Client findClient(String name) {
        for (Client client : clients) {
            if (client.getName().equals(name)) {
                return client;
            }
        }
        return null;
    }

    public List<Client> getAllClients() {
        return clients;
    }

    private List<Client> loadClientsFromJSON() {
        List<Client> loadedClients = new ArrayList<>();
        try {
            File file = new File(JSON_FILE_PATH);
            if (file.exists()) {
                loadedClients = objectMapper.readValue(file, new TypeReference<List<Client>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedClients;
    }

    private void saveClientsToJSON() {
        try {
            objectMapper.writeValue(new File(JSON_FILE_PATH), clients);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class AddClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Implement the logic to add a client
            String name = frmClient.getClientName();
            String contact = frmClient.getClientContact();
            addClient(new Client(name, contact));
            // Update view if necessary
        }
    }

    public class RemoveClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Implement the logic to remove a client
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
            // Implement the logic to find a client
            String name = frmClient.getClientName();
            Client client = findClient(name);
            if (client != null) {
                // Update view with client details
                frmClient.setClientContact(client.getContact());
            }
        }
    }
}
