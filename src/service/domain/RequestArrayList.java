package service.domain;

import java.util.ArrayList;
import java.util.List;

public class RequestArrayList implements IServiceList {

    private List<ServiceRequest> requests;

    public RequestArrayList() {
        requests = new ArrayList<>();
    }

    private ServiceRequest searchRequest(String requestId) {
        ServiceRequest request = null;
        boolean found = false;
        int i = 0;

        while(i < requests.size() && !found) {
            request = requests.get(i);
            if(request.getId().equals(requestId))
                found = true;
            else
                i++;
        }

        if(found)
            return request;
        else
            return null;
    }

    @Override
    public void createRequest(String id, Client client) {
        ServiceRequest request = new ServiceRequest(id, client);
        requests.add(request);
    }

    @Override
    public void assignTechnician(String requestId, String technicianId, String serviceDate) {
        Technician technician = null;
        List<Technician> technicians = ExistingData.retrieveTechnician();
        ServiceRequest selectedRequest = this.searchRequest(requestId);
        boolean found = false;
        int i = 0;

//        while(i < technicians.size() && !found) {
//            technician = technicians.get(i);
//            if(technician.getId().equals(technicianId)) {
//                found = true;
//            } else {
//                i++;
//            }
//        }

        if(found) {
            selectedRequest.setTechnician(technician);
            selectedRequest.setServiceDate(serviceDate);
        } else {
            System.out.println("Technician not found!");
        }
    }

    @Override
    public void updateServiceCharge(String requestId, double serviceCharge) {
        ServiceRequest selectedRequest = this.searchRequest(requestId);
        selectedRequest.setServiceCharge(serviceCharge);
    }

}
