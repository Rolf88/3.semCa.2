/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.Person;
import facade.PersonFacade;
import infrastructure.IPersonFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import rest.models.ContactInfoPerson;

/**
 * REST Web Service
 *
 * @author AlexanderSteen
 */
@Path("person")
public class PersonResource {

    @Context
    private UriInfo context;

    private IPersonFacade facade;
    private Gson gson;

    /**
     * Creates a new instance of PersonResource
     */
    public PersonResource() {
        this(new PersonFacade(Persistence.createEntityManagerFactory("3.semCa.3PU")));
    }

    public PersonResource(IPersonFacade personFacade) {
        this.facade = personFacade;
        gson = new Gson();
    }

    /**
     * Retrieves representation of an instance of rest.PersonResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    @Path("complete/{id}")
    public Person getPersonComplete(@PathParam("id") int id) {
        Person p = facade.getPerson(id);

        if (p == null) {
            throw new NullPointerException();
        }

        return p;
    }

    @GET
    @Produces("application/json")
    @Path("contactinfo/{id}")
    public ContactInfoPerson getPersonContactInfo(@PathParam("id") int id) {
        Person p = facade.getPerson(id);

        if (p == null) {
            throw new NullPointerException();
        }

        ContactInfoPerson cp = new ContactInfoPerson(p.getFirstName(), p.getLastName(), p.getPhones(), p.getEmail());
        return cp;
    }

    @GET
    @Produces("application/json")
    @Path("complete")
    public List<Person> getPersonsComplete() {
        List<Person> p = facade.getPersons();

        if (p.isEmpty()) {
            throw new NullPointerException();
        }

        return p;
    }

    @GET
    @Produces("application/json")
    @Path("contactinfo")
    public List<ContactInfoPerson> getPersonsContactInfo() {
        List<Person> persons = facade.getPersons();
        List<ContactInfoPerson> cPersons = new ArrayList();

        for (Person p : persons) {
            cPersons.add(new ContactInfoPerson(p.getFirstName(), p.getLastName(), p.getPhones(), p.getEmail()));
        }

        return cPersons;
    }

    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
