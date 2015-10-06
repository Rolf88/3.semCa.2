package facade;

import entity.Address;
import entity.CityInfo;
import entity.Hobby;
import entity.Person;
import javax.persistence.Persistence;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PersonFacadeTest {

    private PersonFacade personFacade;

    @Before
    public void setUp() {
        this.personFacade = new PersonFacade(Persistence.createEntityManagerFactory("3.semCa.2PU"));
    }

    @Test
    public void testIsAbleToGetAPerson() {
        Person person = this.personFacade.getPerson(1);

        assertNotNull(person);
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
}
