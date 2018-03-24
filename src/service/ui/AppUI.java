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
            System.out.println("3 - Assign technician");
            System.out.println("4 - Update service charge");
            System.out.println("5 - Exit");

            System.out.println("Please selection an option from the menu above (1-5): ");
            choice = scanner.nextInt();

            while(choice < 1 || choice > 5){
                System.out.println("Invalid choice");
                System.out.println("Please enter your option again (1-5): ");
                choice = scanner.nextInt();
            }

            switch (choice) {
                case 1: displayAllRequest();
                break;
                case 2: createRequest();
                break;
                case 3: assignTechnician();
                break;
                case 4: updateServiceCharge();
                break;
            }
        } while(choice != 5);
    }

    // Just for checking purpose, it is not included in the diagrams
    private void displayAllRequest() {
        List<ServiceRequest> requests = controller.displayAllRequests();

        if(requests.size() == 0) {
            System.out.println("The service request list is currently empty.");
        } else {
            for(int i = 0; i < requests.size(); i++) {
                System.out.println("Details of Service Request " + requests.get(i).getId() + ":");
                System.out.println("Service Charge: RM" + requests.get(i).getServiceCharge());
                System.out.println("Client Name: " + requests.get(i).getClient().getName());
                if(requests.get(i).getTechnician() != null) {
                    System.out.println("Technician Name: " + requests.get(i).getTechnician().getName());
                    System.out.println("Service Date: " + requests.get(i).getServiceDate());
                }
                System.out.println();
            }
        }
    }

    private void createRequest() {

        Client selectedClient = null;

        do {
            selectedClient = searchClient();
        } while(selectedClient == null);

        displayClient(selectedClient);

        System.out.println("Are you sure you want to create new service request with this client? (Y/N): ");
        char confirmation = scanner.next().charAt(0);

        if(confirmation == 'Y' || confirmation == 'y') {
            System.out.println("Enter request ID (Eg: 3001, 3002, 3003...):  ");
            String skip2 = scanner.nextLine();
            String requestId = scanner.nextLine();
            controller.createRequest(requestId, selectedClient);
            System.out.println("Request created successfully!");
        } else {
            System.out.print("Thank you!");
        }
    }

    private void searchRequest() {
        String requestId;
        ServiceRequest selectedRequest;

        do{
            System.out.println("Enter ID for the service request that you wish to update it (Eg: 3001, 3002, 3003...): ");

            do{
                requestId = scanner.nextLine();
            } while(requestId.isEmpty());

            selectedRequest = controller.searchRequest(requestId);

            if(selectedRequest != null) {
                System.out.println("Request ID: " + selectedRequest.getId());
                System.out.println("Client Name: " + selectedRequest.getClient().getName());
                if(selectedRequest.getTechnician() != null) {
                    System.out.println("Technician Name: " + selectedRequest.getTechnician().getName());
                    System.out.println("Service Date: " + selectedRequest.getServiceDate());
                }
                if(selectedRequest.getServiceCharge() != 0.0)
                    System.out.println("Service Charge: " + selectedRequest.getServiceCharge());
            } else {
                System.out.println("Request ID is not found!");
                System.out.println("Please try again.");
            }

        } while(selectedRequest == null);
    }

    private void assignTechnician() {

        Technician selectedTechnician = null;

        searchRequest();

        do {
            selectedTechnician = searchTechnician();
        } while(selectedTechnician == null);

        displayTechnician(selectedTechnician);

        System.out.println("Are you sure you want to assign this technician to this service request? (Y/N): ");
        char confirmation = scanner.next().charAt(0);

        if(confirmation == 'Y' || confirmation == 'y') {
            System.out.println("Enter service date (DD/MM/YYYY): ");
            String skip3 = scanner.nextLine();
            String serviceDate = scanner.nextLine();
            controller.assignTechnician(selectedTechnician, serviceDate);
            System.out.println("Technician assigned successfully!");
        } else {
            System.out.print("Thank you!");
        }
    }

    private void updateServiceCharge() {

        searchRequest();

        System.out.println("Enter service charge in (RM): ");
        double serviceCharge = scanner.nextDouble();
        controller.updateServiceCharge(serviceCharge);
        System.out.println("Service charge updated successfully!");
    }

    private Client searchClient() {

        boolean found = false;
        int i = 0;
        Client selectedClient = null;
        String clientId;
        List<Client> clients = ExistingData.retrieveClients();

        System.out.println("Enter client ID (Eg: 1001, 1002, 1003...):  ");

        do {
            clientId = scanner.nextLine();
        } while(clientId.isEmpty());

        while(i < clients.size() && !found) {
            selectedClient = clients.get(i);
            if(selectedClient.getId().equals(clientId)) {
                found = true;
            } else {
                i++;
            }
        }

        if(found) {
            return selectedClient;
        } else {
            System.out.println("Client ID is not found!");
            System.out.println("Please try again.");
            return null;
        }
    }

    private Technician searchTechnician(String id) {

        boolean found = false;
        int i = 0;
        String technicianId;
        Technician selectedTechnician = null;
        List<Technician> technicians = ExistingData.retrieveTechnician();

        System.out.println("Enter technician ID (Eg: 2001, 2002, 2003...) ");

        do{
            technicianId = scanner.nextLine();
        } while(technicianId.isEmpty());

        while(!found && i < technicians.size()) {
            selectedTechnician = technicians.get(i);
            if(selectedTechnician.getId().equals(id)) {
                found = true;
            } else {
                i++;
            }
        }

        if(found) {
            return selectedTechnician;
        } else {
            System.out.println("Technician not found!");
            System.out.println("Please try again.");
            return null;
        }
    }

    public void displayClient(Client selectedClient) {

        System.out.println("Details of selected client: ");
        System.out.println("Name: " + selectedClient.getName());
        System.out.println("Address: " + selectedClient.getAddress());
        System.out.println("Contact: " + selectedClient.getContact());
        System.out.println();
    }

    public void displayTechnician(Technician selectedTechnician) {
        System.out.println("Details of selected technician: ");
        System.out.println("Name: " + selectedTechnician.getName());
        System.out.println("Sex: " + selectedTechnician.getSex());
        System.out.println("Address: " + selectedTechnician.getAddress());
        System.out.println("Contact: " + selectedTechnician.getContact());
        System.out.println();
    }
}
