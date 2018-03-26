package service.domain;

import service.dbhelper.Client;
import service.dbhelper.Technician;

import java.util.List;

public interface IServiceList {

    public ServiceRequest searchRequest(String id);

    public List<ServiceRequest> displayAllRequests();

    public void createRequest(String requestId, Client selectedClient);

    public void assignTechnician(Technician selectedTechnician, String serviceDate);

    public void updateServiceCharge(double serviceCharge);
}
