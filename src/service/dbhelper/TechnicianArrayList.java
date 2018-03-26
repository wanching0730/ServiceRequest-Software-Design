package service.dbhelper;

import java.util.ArrayList;
import java.util.List;

public class TechnicianArrayList extends PersonArrayList {

    private List<Person> technicians = new ArrayList<>();

    public TechnicianArrayList() {
        init();
    }

    public void init() {
        technicians.add(new Technician("2001", "Johny", "Ampang", "017-2222222"));
        technicians.add(new Technician("2002", "Junnie", "Damansara", "017-6666666"));
        technicians.add(new Technician("2003", "Kenny",  "Bangi", "017-7777777"));
        technicians.add(new Technician("2004", "Jenny", "Damansara", "017-3333333"));
        technicians.add(new Technician("2005", "Elly", "Ampang", "017-9999999"));

        persons = technicians;
    }

}
