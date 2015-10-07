package rest.models;

import entity.Phone;
import java.util.List;

public class ContactInfoPerson {

    private String firstName;
    private String lastName;
    private List<Phone> phones;
    private String email;

    public ContactInfoPerson() {
    }

    public ContactInfoPerson(String firstName, String lastName, List<Phone> phones, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phones = phones;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
