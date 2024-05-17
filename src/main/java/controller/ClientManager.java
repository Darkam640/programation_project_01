package controller;

import model.Client;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import view.FRM_Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientManager {
    private List<Client> clients;
    private FRM_Client frmClient;

    private static final String JSON_FILE_PATH = "clients.json";

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
        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                String name = (String) jsonObject.get("name");
                String contact = (String) jsonObject.get("contact");
                loadedClients.add(new Client(name, contact));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return loadedClients;
    }

    private void saveClientsToJSON() {
        JSONArray jsonArray = new JSONArray();
        for (Client client : clients) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", client.getName());
            jsonObject.put("contact", client.getContact());
            jsonArray.add(jsonObject);
        }
        try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
            writer.write(jsonArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class AddClientListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Implement the logic to add a client
            String name = frmClient.getClientName();
            String contact = frmClient.getClientContact();
            addClient(new Client(name, contact));
            // Update view if necessary
        }
    }

    private class RemoveClientListener implements ActionListener {
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

    private class FindClientListener implements ActionListener {
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
