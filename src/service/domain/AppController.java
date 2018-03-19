package service.domain;

public class AppController {

    private IServiceList serviceList;

    public AppController() {
        serviceList = new RequestArrayList();
    }

    public void createRequest(String id, Client client) {
        serviceList.createRequest(id, client);
    }

    public void assignTechnician(String requestId, String technicianId, String serviceDate) {
        serviceList.assignTechnician(requestId, technicianId, serviceDate);
    }

    public void updateServiceCharge(String requestId, double serviceCharge) {
        serviceList.updateServiceCharge(requestId, serviceCharge);
    }
}
