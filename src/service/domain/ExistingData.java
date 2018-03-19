package service.domain;

import java.util.ArrayList;
import java.util.List;

public class ExistingData {

    public static List<Client> retrieveClients() {
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("1001", "Tan Ah Meng", "Cheras", "016-7777777"));
        clients.add(new Client("1002", "Chan Kok Leng", "Kajang", "016-8888888"));
        clients.add(new Client("1003", "Teng Lee Ming", "Ampang", "016-4444444"));
        clients.add(new Client("1004", "Kok Tien Jian", "Damansara", "016-5555555"));
        clients.add(new Client("1005", "Matthew Ong", "Bangi", "016-1111111"));

        return clients;
    }

    public static List<Technician> retrieveTechnician() {
        List<Technician> technicians = new ArrayList<>();
        technicians.add(new Technician("2001", "Johny", 'M', "Ampang", "017-2222222"));
        technicians.add(new Technician("2002", "Junnie", 'F', "Damansara", "017-6666666"));
        technicians.add(new Technician("2003", "Kenny", 'M', "Bangi", "017-7777777"));
        technicians.add(new Technician("2004", "Jenny", 'F', "Damansara", "017-3333333"));
        technicians.add(new Technician("2005", "Elly", 'F', "Ampang", "017-9999999"));

        return technicians;
    }

}
