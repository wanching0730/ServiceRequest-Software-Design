package service.domain;

public interface IServiceList {

    public void createRequest(String id, Client client);

    public void assignTechnician(String requestId, String technicianId, String serviceDate);

    public void updateServiceCharge(String requestId, double serviceCharge);
}
