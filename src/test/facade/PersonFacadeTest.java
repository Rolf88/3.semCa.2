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

        Person createdPerson = this.personFacade.addPerson(personToCreate);

        assertEquals(4, this.personFacade.getPersons().size());
        assertNotNull(createdPerson);
        assertEquals(personToCreate.getFirstName(), createdPerson.getFirstName());
        assertEquals(personToCreate.getLastName(), createdPerson.getLastName());
        assertEquals(personToCreate.getEmail(), createdPerson.getEmail());
        assertNull(createdPerson.getAddress());
        assertEquals(2, createdPerson.getHobbies().size());
    }

    @Test
    public void testAddCompany_IsAbleToCreateANewCompany() {
        Company companyToCreate = new Company();
        companyToCreate.setCvr("123123123");
        companyToCreate.setDescription("CompnayBedes");
        companyToCreate.setName("fsiodjgsdfg");
        companyToCreate.setMarketValue(1233453);
        companyToCreate.setNumEmployees(9);

        Company createdCompany = this.personFacade.addCompany(companyToCreate);

        assertNotNull(createdCompany);
        assertEquals(companyToCreate.getName(), createdCompany.getName());
        assertEquals(companyToCreate.getCvr(), createdCompany.getCvr());
        assertEquals(companyToCreate.getDescription(), createdCompany.getDescription());
        assertEquals(companyToCreate.getMarketValue(), createdCompany.getMarketValue());
        assertEquals(companyToCreate.getNumEmployees(), createdCompany.getNumEmployees());
    }

    @Test
    public void testAddCompany_IsAbleToCreateANewCompany_WithAAddress() {
        CityInfo city = new CityInfo();
        city.setCity("CompanyCity");
        city.setZip("24234C");

        Address address = new Address();
        address.setStreet("Company Road 2");
        address.setAdditionalInfo("Companty");
        address.setCity(city);

        Company companyToCreate = new Company();
        companyToCreate.setCvr("123123123");
        companyToCreate.setDescription("CompnayBedes");
        companyToCreate.setName("fsiodjgsdfg");
        companyToCreate.setMarketValue(1233453);
        companyToCreate.setNumEmployees(9);
        companyToCreate.setAddress(address);

        Company createdCompany = this.personFacade.addCompany(companyToCreate);

        assertNotNull(createdCompany);
        assertEquals(companyToCreate.getName(), createdCompany.getName());
        assertEquals(companyToCreate.getCvr(), createdCompany.getCvr());
        assertEquals(companyToCreate.getDescription(), createdCompany.getDescription());
        assertEquals(companyToCreate.getMarketValue(), createdCompany.getMarketValue());
        assertEquals(companyToCreate.getNumEmployees(), createdCompany.getNumEmployees());
        assertEquals(companyToCreate.getAddress(), createdCompany.getAddress());
    }

    @Test(expected = NullPointerException.class)
    public void testDeletePerson_ShouldThrowExceptionIfPersonIsNull() {
        this.personFacade.deletePerson(null);
    }

    @Test
    public void testDeletePerson_IsAbleToDelete() {
        Person person = this.personFacade.getPerson(1);

        this.personFacade.deletePerson(person);

        assertEquals(2, this.personFacade.getPersons().size());
    }
    
    @Test(expected = NullPointerException.class)
    public void testUpdatePerson_ThrowException_IfPersonIsNull(){
        this.personFacade.updatePerson(null);
    }

    @Test
    public void testUpdatePerson_IsAbleToUpdate() {
        Person person = this.personFacade.getPerson(1);
        person.setFirstName("Brian");
        person.setLastName("Madsen");
        person.getHobbies().remove(0);

        person.getAddress().setStreet("Testvej 2");
        person.getAddress().setAdditionalInfo(null);

        CityInfo city = new CityInfo();
        city.setCity("Smørum");
        city.setZip("2765");

        person.getAddress().setCity(city);

        this.personFacade.updatePerson(person);

        Person updatedPerson = this.personFacade.getPerson(1);
        assertNotNull("person is null", updatedPerson);
        assertEquals(person.getFirstName(), updatedPerson.getFirstName());
        assertEquals(person.getLastName(), updatedPerson.getLastName());

        assertNotNull(updatedPerson.getHobbies());
        assertEquals(person.getHobbies().size(), updatedPerson.getHobbies().size());

        assertNotNull(updatedPerson.getAddress());
        assertEquals(person.getAddress().getStreet(), updatedPerson.getAddress().getStreet());
        assertEquals(person.getAddress().getAdditionalInfo(), updatedPerson.getAddress().getAdditionalInfo());

        assertNotNull(updatedPerson.getAddress().getCity());
        assertEquals(person.getAddress().getCity().getCity(), updatedPerson.getAddress().getCity().getCity());
        assertEquals(person.getAddress().getCity().getZip(), updatedPerson.getAddress().getCity().getZip());
    }
}
