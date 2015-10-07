package facade;

import entity.Address;
import entity.CityInfo;
import entity.Company;
import entity.Hobby;
import entity.Person;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Persistence;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PersonFacadeTest {

    private PersonFacade personFacade;

    private static int runIndex = 0;

    @Before
    public void setUp() {
        // This is need to drop and create the tables for every run
        Map<String, String> props = new HashMap<>();
        props.put("weblogic.application-id", "test-run-" + (++runIndex));

        Persistence.generateSchema("3.semCa.3PU", props);

        this.personFacade = new PersonFacade(Persistence.createEntityManagerFactory("3.semCa.3PU", props));
    }

    @Test
    public void testGetPerson_ShouldReturnNullIfPersonNotFound() {
        Person person = this.personFacade.getPerson(9999);

        assertNull(person);
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

    @Test(expected = NullPointerException.class)
    public void testAddPerson_ShouldThrowExceptionIfPersonIsNull() {
        this.personFacade.addPerson(null);
    }

    @Test
    public void testAddPerson_IsAbleToCreateANewPerson_WithNoHobbiesOrAddress() {
        Person personToCreate = new Person();
        personToCreate.setFirstName("Mads");
        personToCreate.setLastName("Mikkelsen");
        personToCreate.setEmail("mads@mikkelsen.dk");

        int numberOfPersons = this.personFacade.getPersons().size();

        Person createdPerson = this.personFacade.addPerson(personToCreate);

        assertEquals(numberOfPersons + 1, this.personFacade.getPersons().size());
        assertNotNull(createdPerson);
        assertEquals(personToCreate.getFirstName(), createdPerson.getFirstName());
        assertEquals(personToCreate.getLastName(), createdPerson.getLastName());
        assertEquals(personToCreate.getEmail(), createdPerson.getEmail());
        assertNull(createdPerson.getAddress());
        assertEquals(0, createdPerson.getHobbies().size());
    }

    @Test
    public void testAddPerson_IsAbleToCreateANewPerson_WithHobbies_AndNoAddress() {
        Person personToCreate = new Person();
        personToCreate.setFirstName("Morten");
        personToCreate.setLastName("Poulsen");
        personToCreate.setEmail("morten@poulsen.dk");

        Hobby eatingHobby = new Hobby();
        eatingHobby.setName("Eating");
        eatingHobby.setDescription("I like to eat");

        Hobby runningHobby = new Hobby();
        runningHobby.setName("Running");
        runningHobby.setDescription("Can run a mile");

        personToCreate.getHobbies().add(eatingHobby);
        personToCreate.getHobbies().add(runningHobby);

        int numberOfPersons = this.personFacade.getPersons().size();

        Person createdPerson = this.personFacade.addPerson(personToCreate);

        assertEquals(numberOfPersons + 1, this.personFacade.getPersons().size());
        assertNotNull(createdPerson);
        assertEquals(personToCreate.getFirstName(), createdPerson.getFirstName());
        assertEquals(personToCreate.getLastName(), createdPerson.getLastName());
        assertEquals(personToCreate.getEmail(), createdPerson.getEmail());
        assertNull(createdPerson.getAddress());
        assertEquals(2, createdPerson.getHobbies().size());
    }
}
