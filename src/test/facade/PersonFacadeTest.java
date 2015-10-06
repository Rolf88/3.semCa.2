package facade;

import entity.Address;
import entity.CityInfo;
import entity.Company;
import entity.Hobby;
import entity.Person;
import java.util.List;
import javax.persistence.Persistence;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PersonFacadeTest {

    private PersonFacade personFacade;

    @Before
    public void setUp() {
        this.personFacade = new PersonFacade(Persistence.createEntityManagerFactory("3.semCa.3PU"));
    }

    @Test
    public void testIsAbleToGetAPerson() {
        Person person = this.personFacade.getPerson(1);

        assertNotNull("person is null", person);
        assertEquals("Kim", person.getFirstName());
        assertEquals("Larsen", person.getLastName());

        assertNotNull(person.getHobbies());

        Hobby fishingHobby = person.getHobbies().get(0);
        assertEquals("Fishing", fishingHobby.getName());
        assertEquals("Likes to fish", fishingHobby.getDescription());

        Address address = person.getAddress();
        assertNotNull(address);
        assertEquals("Nørregade 2", address.getStreet());
        assertEquals("2 th", address.getAdditionalInfo());

        CityInfo city = address.getCity();
        assertNotNull(city);
        assertEquals("København Ø", city.getCity());
        assertEquals("2200", city.getZip());
    }

    @Test
    public void testIsAbleToGetAListPersons() {
        List<Person> persons = this.personFacade.getPersons();

        assertNotNull(persons);
        assertEquals(3, persons.size());
    }

    @Test
    public void testIsAbleToGetAListPersons_ByHobby() {
        List<Person> persons = this.personFacade.findPersonsWithHobby("Fishing");

        assertNotNull(persons);
        assertEquals(1, persons.size());
    }

    @Test
    public void testIsAbleToGetAListPersons_ByZipcode() {
        String zipCode = "2200";
        List<Person> persons = this.personFacade.getPersons(zipCode);

        assertNotNull(persons);
        assertEquals(2, persons.size());

        for (Person person : persons) {
            assertNotNull(person.getAddress());
            assertNotNull(person.getAddress().getCity());
            assertEquals(zipCode, person.getAddress().getCity().getZip());
        }
    }

    @Test
    public void testIsAbleToFindACompanyByCVR() {
        String cvr = "123456789";
        Company company = this.personFacade.getCompany(cvr);

        assertNotNull(company);
        assertEquals("MyCompany", company.getName());
        assertEquals(cvr, company.getCvr());
        assertEquals("MyCompany test description", company.getDescription());
        assertEquals(234, company.getMarketValue());
        assertEquals(4, company.getNumEmployees());

        Address address = company.getAddress();
        assertNotNull(address);
        assertEquals("Nørregade 3", address.getStreet());
        assertEquals("2 th", address.getAdditionalInfo());

        CityInfo city = address.getCity();
        assertNotNull(city);
        assertEquals("København Ø", city.getCity());
        assertEquals("2200", city.getZip());
    }
}
