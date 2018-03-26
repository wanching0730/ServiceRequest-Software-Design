package service.domain;

import service.dbhelper.Client;
import service.dbhelper.Technician;

public class ServiceRequest {

    private String id;
    private String serviceDate;
    private double serviceCharge;
    private Client client;
    private Technician technician;

    public ServiceRequest(String id, Client client) {
        this.id = id;
        this.client = client;
        this.serviceDate = "";
        this.serviceCharge = 0.0;
        this.technician = null;
    }

    public String getId() {
        return id;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public double getServiceCharge() {
        return serviceCharge;
    }

    public Client getClient() {
        return client;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    public void setServiceCharge(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }
}
