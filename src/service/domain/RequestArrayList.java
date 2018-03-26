package service.domain;

import service.DbHelper.Client;
import service.DbHelper.Technician;

import java.util.ArrayList;
import java.util.List;

public class RequestArrayList implements IServiceList {

    private List<ServiceRequest> requests;
    private ServiceRequest selectedRequest = null;

    public RequestArrayList() {
        requests = new ArrayList<>();
    }

    @Override
    public ServiceRequest searchRequest(String requestId) {
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

        if(found) {
            selectedRequest = request;
            return request;
        }

        else
            return null;
    }

    @Override
    public List<ServiceRequest> displayAllRequests() {
        return requests;
    }

    @Override
    public void createRequest(String requestId, Client selectedClient) {
        ServiceRequest request = new ServiceRequest(requestId, selectedClient);
        requests.add(request);
    }

    @Override
    public void assignTechnician(Technician selectedTechnician, String serviceDate) {
        selectedRequest.setTechnician(selectedTechnician);
        selectedRequest.setServiceDate(serviceDate);
    }

    @Override
    public void updateServiceCharge(double serviceCharge) {
        selectedRequest.setServiceCharge(serviceCharge);
    }

}
