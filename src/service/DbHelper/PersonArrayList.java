package service.DbHelper;

import java.util.List;

public abstract class PersonArrayList {

    protected List<Person> persons;

    public List<Person> retrieve(){
        return persons;
    }

    public Person search(String id) {

        boolean found = false;
        int i = 0;
        Person selectedPerson = null;

        while(i < persons.size() && !found) {
            selectedPerson = persons.get(i);
            if(selectedPerson.getId().equals(id)) {
                found = true;
            } else {
                i++;
            }
        }

        if(found) {
            return selectedPerson;
        } else {
            return null;
        }
    }
}
