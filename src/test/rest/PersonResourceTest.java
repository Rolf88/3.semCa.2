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

    private Mockery context = new Mockery();

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
}