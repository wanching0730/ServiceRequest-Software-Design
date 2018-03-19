package service.domain;

public class Technician {

    private String id;
    private String name;
    private char sex;
    private String address;
    private String contact;

    public Technician(String id, String name, char sex, String address, String contact) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.address = address;
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public char getSex() {
        return sex;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }
}
