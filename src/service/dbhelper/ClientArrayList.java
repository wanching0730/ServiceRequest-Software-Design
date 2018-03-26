package service.dbhelper;

import java.util.ArrayList;
import java.util.List;

public class ClientArrayList extends PersonArrayList {

    private List<Person> clients = new ArrayList<>();

    public ClientArrayList() {
        init();
    }

    public void init() {
        clients.add(new Client("1001", "Tan Ah Meng", "Cheras", "016-7777777"));
        clients.add(new Client("1002", "Chan Kok Leng", "Kajang", "016-8888888"));
        clients.add(new Client("1003", "Teng Lee Ming", "Ampang", "016-4444444"));
        clients.add(new Client("1004", "Kok Tien Jian", "Damansara", "016-5555555"));
        clients.add(new Client("1005", "Matthew Ong", "Bangi", "016-1111111"));

        persons = clients;
    }
}
