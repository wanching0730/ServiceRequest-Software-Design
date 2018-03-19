package service.ui;

import service.domain.*;

import java.util.List;
import java.util.Scanner;

public class AppUI implements IUserInterface {

    private Scanner scanner;
    private AppController controller;

    public AppUI() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void setController(AppController controller) {
        this.controller = controller;
    }

    @Override
    public void start() {
        int choice;

        do {
            System.out.println("Do you want to:");
            System.out.println("1 - Display all service requests");
            System.out.println("2 - Create new service request");
            System.out.println("3 - Update service request");
            System.out.println("4 - Exit");

            System.out.println("Please selection an option from the menu above (1-4): ");
            choice = scanner.nextInt();

            while(choice < 1 || choice > 4){
                System.out.println("Invalid choice");
                System.out.println("Please enter your option again (1-4): ");
                choice = scanner.nextInt();
            }

            switch (choice) {
                case 2: createRequest();
                break;
            }


        } while(choice != 4);

    }

    private void createRequest() {
        System.out.println("Enter client ID: ");
        String skip1 = scanner.nextLine();
        String clientId = scanner.nextLine();

        Client selectedClient = searchClient(clientId);
        System.out.println("Name: " + selectedClient.getName());
        System.out.println("Address: " + selectedClient.getAddress());
        System.out.println("Contact: " + selectedClient.getContact());
        System.out.println("Are you sure you want to create new service request with this client? (Y/N): ");
        String skip2 = scanner.nextLine();
        boolean confirmation = scanner.nextBoolean();

        if(confirmation) {
            System.out.println("Enter request ID: ");
            String skip3 = scanner.nextLine();
            String requestId = scanner.nextLine();
            controller.createRequest(requestId, selectedClient);
        } else {
            return;
        }
    }

    private Client searchClient(String id) {
        Client client = null;
        List<Client> clients = ExistingData.retrieveClients();
        boolean found = false;
        int i = 0;

        while(i < clients.size() && !found) {
            client = clients.get(i);
            if(client.getId().equals(id)) {
                found = true;
            } else {
                i++;
            }
        }

        if(found) {
            return client;
        } else {
            System.out.println("Technician not found!");
            return null;
        }
    }
}
