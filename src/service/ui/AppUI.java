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
                case 1: displayAllRequest();
                break;
                case 2: createRequest();
                break;
                case 3: updateRequest();
                break;
            }
        } while(choice != 4);
    }

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
        System.out.println("Enter client ID: ");
        String skip1 = scanner.nextLine();
        String clientId = scanner.nextLine();

        Client selectedClient = searchClient(clientId);
        System.out.println("Details of selected client: ");
        System.out.println("Name: " + selectedClient.getName());
        System.out.println("Address: " + selectedClient.getAddress());
        System.out.println("Contact: " + selectedClient.getContact());
        System.out.println();
        System.out.println("Are you sure you want to create new service request with this client? (Y/N): ");
        char confirmation = scanner.next().charAt(0);

        if(confirmation == 'Y' || confirmation == 'y') {
            System.out.println("Enter request ID: ");
            String skip2 = scanner.nextLine();
            String requestId = scanner.nextLine();
            controller.createRequest(requestId, selectedClient);
            System.out.println("Request created successfully!");
        } else {
            System.out.print("Thank you!");
        }
    }

    private void updateRequest() {

        int choice;
        String repeat;
        String requestId;

        do {
            System.out.println("Enter request ID that you wish to update it: ");

           do{
               requestId = scanner.nextLine();
           } while(requestId.isEmpty());

            ServiceRequest request = controller.getSelectedRequest(requestId);
            System.out.println("Details of selected service request: ");
            System.out.println("ID: " + request.getId());
            System.out.println("Service charge: " + request.getServiceCharge());
            System.out.println("Client Name: " + request.getClient().getName());
            if(request.getTechnician() != null) {
                System.out.println("Technician Name: " + request.getTechnician().getName());
                System.out.println("Service Date: " + request.getServiceDate());
            }

            System.out.println();

            System.out.println("Do you want to:");
            System.out.println("1 - Assign technician");
            System.out.println("2 - Update service charge");

            System.out.println("Please selection an option from the menu above (1-3): ");
            choice = scanner.nextInt();

            while(choice < 1 || choice > 3) {
                System.out.println("Invalid choice");
                System.out.println("Please enter your option again (1-3): ");
                choice = scanner.nextInt();
            }

            switch (choice) {
                case 1: assignTechnician();
                break;
                case 2: updateServiceCharge();
                break;
            }

            System.out.println("Do you want to continue updating service request? (Y/N): ");
            do{
                repeat = scanner.nextLine();
            } while (repeat.isEmpty());


        } while(repeat.charAt(0) == 'Y' || repeat.charAt(0) == 'y');
    }

    private void assignTechnician() {
        System.out.println("Enter technician ID: ");
        String skip1 = scanner.nextLine();
        String technicianId = scanner.nextLine();

        Technician selectedTechnician = searchTechnician(technicianId);

        System.out.println("Details of selected technician: ");
        System.out.println("Name: " + selectedTechnician.getName());
        System.out.println("Sex: " + selectedTechnician.getSex());
        System.out.println("Address: " + selectedTechnician.getAddress());
        System.out.println("Contact: " + selectedTechnician.getContact());
        System.out.println();
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
        System.out.println("Enter service charge in (RM): ");
        double serviceCharge = scanner.nextDouble();
        controller.updateServiceCharge(serviceCharge);
        System.out.println("Service charge updated successfully!");
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
            System.out.println("Client not found!");
            return null;
        }
    }

    private Technician searchTechnician(String id) {
        Technician technician = null;
        List<Technician> technicians = ExistingData.retrieveTechnician();
        boolean found = false;
        int i = 0;

        while(!found && i < technicians.size()) {
            technician = technicians.get(i);
            if(technician.getId().equals(id)) {
                found = true;
            } else {
                i++;
            }
        }

        if(found) {
            return technician;
        } else {
            System.out.println("Technician not found!");
            return null;
        }
    }
}
