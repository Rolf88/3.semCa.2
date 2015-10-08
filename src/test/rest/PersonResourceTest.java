/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import infrastructure.IPersonFacade;
import java.util.ArrayList;
import org.jmock.Expectations;
import static org.jmock.Expectations.returnValue;
import org.jmock.Mockery;
import org.junit.Test;
import rest.exceptions.PersonNotFoundException;

/**
 *
 * @author priva_000
 */
public class PersonResourceTest {

    private final Mockery context = new Mockery();

    @Test(expected = PersonNotFoundException.class)
    public void testComplete_ThrowException_IfNoPersonsExists() throws PersonNotFoundException {
        final IPersonFacade personFacade = context.mock(IPersonFacade.class);

        context.checking(new Expectations() {
            {
                oneOf(personFacade).getPersons();
                will(returnValue(new ArrayList<>()));
            }
        });

        PersonResource resource = new PersonResource(personFacade);
        resource.getPersonsComplete();
    }

    @Test(expected = PersonNotFoundException.class)
    public void testContactInfo_ThrowException_IfNoPersonsExists() throws PersonNotFoundException {
        final IPersonFacade personFacade = context.mock(IPersonFacade.class);

        context.checking(new Expectations() {
            {
                oneOf(personFacade).getPersons();
                will(returnValue(new ArrayList<>()));
            }
        });

        PersonResource resource = new PersonResource(personFacade);
        resource.getPersonsContactInfo();
    }
    
    @Test(expected = PersonNotFoundException.class)
    public void testContactInfo_ById_ThrowException_IfNoPersonsExists() throws PersonNotFoundException {
        final IPersonFacade personFacade = context.mock(IPersonFacade.class);

        context.checking(new Expectations() {
            {
                oneOf(personFacade).getPerson(1);
                will(returnValue(null));
            }
        });

        PersonResource resource = new PersonResource(personFacade);
        resource.getPersonContactInfo(1);
    }
    
    @Test(expected = PersonNotFoundException.class)
    public void testComplete_ById_ThrowException_IfNoPersonsExists() throws PersonNotFoundException {
        final IPersonFacade personFacade = context.mock(IPersonFacade.class);

        context.checking(new Expectations() {
            {
                oneOf(personFacade).getPerson(1);
                will(returnValue(null));
            }
        });

        PersonResource resource = new PersonResource(personFacade);
        resource.getPersonComplete(1);
    }   

    @Test(expected = PersonNotFoundException.class)
    public void testContactInfoPersonByHobby_ThrowException_IfNoPersonsExists() throws PersonNotFoundException {
        final IPersonFacade personFacade = context.mock(IPersonFacade.class);
        final String hobbyName = "randomHobbyName";
        
        context.checking(new Expectations() {
            {
                oneOf(personFacade).findPersonsWithHobby(hobbyName);
                will(returnValue(new ArrayList<>()));
            }
        });

        PersonResource resource = new PersonResource(personFacade);
        resource.getContactInfoPersonByHobby(hobbyName);
    }  

    @Test(expected = PersonNotFoundException.class)
    public void testCompletePersonByHobby_ThrowException_IfNoPersonsExists() throws PersonNotFoundException {
        final IPersonFacade personFacade = context.mock(IPersonFacade.class);
        final String hobbyName = "randomHobbyName";
        
        context.checking(new Expectations() {
            {
                oneOf(personFacade).findPersonsWithHobby(hobbyName);
                will(returnValue(new ArrayList<>()));
            }
        });

        PersonResource resource = new PersonResource(personFacade);
        resource.getCompletePersonByHobby(hobbyName);
    }
}
