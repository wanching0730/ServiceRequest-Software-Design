package service.domain;

import service.dbhelper.Client;
import service.dbhelper.Technician;

import java.util.List;

public class AppController {

    private IServiceList serviceList;

    public AppController() {

        serviceList = new RequestArrayList();
    }

    public ServiceRequest searchRequest(String requestId) {
        return serviceList.searchRequest(requestId);
    }

    public List<ServiceRequest> displayAllRequests() {
        return serviceList.displayAllRequests();
    }

    public void createRequest(String id, Client client) {
        serviceList.createRequest(id, client);
    }

    public void assignTechnician(Technician selectedTechnician, String serviceDate) {
        serviceList.assignTechnician(selectedTechnician, serviceDate);
    }

    public void updateServiceCharge(double serviceCharge) {
        serviceList.updateServiceCharge(serviceCharge);
    }
}
