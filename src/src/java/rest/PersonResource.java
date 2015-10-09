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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rest.exceptions.InvalidDataException;
import rest.exceptions.PersonNotFoundException;
import rest.jsonconverter.JSONConverter;
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("complete/{id}")
    public Response getPersonComplete(@PathParam("id") int id) throws PersonNotFoundException {
        Person p = facade.getPerson(id);

        if (p == null) {
            throw new PersonNotFoundException("Person with given id not found");
        }

        return Response.ok(JSONConverter.PersonToJSON(p)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("contactinfo/{id}")
    public Response getPersonContactInfo(@PathParam("id") int id) throws PersonNotFoundException {
        Person p = facade.getPerson(id);

        if (p == null) {
            throw new PersonNotFoundException("Person with given id not found");
        }

        ContactInfoPerson cp = new ContactInfoPerson(p.getId(), p.getFirstName(), p.getLastName(), p.getPhones(), p.getEmail());

        return Response.ok(JSONConverter.ContactInfoPersonToJSON(cp)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("complete")
    public Response getPersonsComplete() throws PersonNotFoundException {
        List<Person> p = facade.getPersons();

        if (p.isEmpty()) {
            throw new PersonNotFoundException("No persons found");
        }

        return Response.ok(JSONConverter.PersonToJSON(p)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("contactinfo")
    public Response getPersonsContactInfo() throws PersonNotFoundException {
        List<Person> persons = facade.getPersons();

        if (persons.isEmpty()) {
            throw new PersonNotFoundException("No persons found");
        }

        List<ContactInfoPerson> cPersons = new ArrayList();

        for (Person p : persons) {
            cPersons.add(new ContactInfoPerson(p.getId(), p.getFirstName(), p.getLastName(), p.getPhones(), p.getEmail()));
        }

        return Response.ok(JSONConverter.ContactInfoPersonToJSON(cPersons)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("contactinfo/hobby/{hobby}")
    public Response getContactInfoPersonByHobby(@PathParam("hobby") String hobby) throws PersonNotFoundException {
        List<Person> persons = facade.findPersonsWithHobby(hobby);

        if (persons.isEmpty()) {
            throw new PersonNotFoundException("No persons found with given hobby");
        }

        List<ContactInfoPerson> cPersons = new ArrayList();

        for (Person p : persons) {
            cPersons.add(new ContactInfoPerson(p.getId(), p.getFirstName(), p.getLastName(), p.getPhones(), p.getEmail()));
        }

        return Response.ok(JSONConverter.ContactInfoPersonToJSON(cPersons)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("complete/hobby/{hobby}")
    public Response getCompletePersonByHobby(@PathParam("hobby") String hobby) throws PersonNotFoundException {
        List<Person> p = facade.findPersonsWithHobby(hobby);

        if (p.isEmpty()) {
            throw new PersonNotFoundException("No persons found");
        }

        return Response.ok(JSONConverter.PersonToJSON(p)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(String json) throws InvalidDataException {
        Person p = gson.fromJson(json, Person.class);

        if (p.getFirstName().isEmpty() || p.getLastName().isEmpty() || p.getEmail().isEmpty()) {
            throw new InvalidDataException("Firstname, lastname and email must be provided");
        }

        p = facade.addPerson(p);

        return Response.status(Response.Status.CREATED).entity(JSONConverter.PersonToJSON(p)).build();
    }

    @DELETE
    @Path("{id}")
    public void removePerson(@PathParam("id") Integer id) {
        Person p = facade.getPerson(id);
        facade.deletePerson(p);
    }
    
    @PUT
    public Response updatePerson(String json) throws InvalidDataException{
        Person p = gson.fromJson(json, Person.class);
        
        if (p.getFirstName().isEmpty() || p.getLastName().isEmpty() || p.getEmail().isEmpty()) {
            throw new InvalidDataException("Firstname, lastname and email must be provided");
        }
        
        p = facade.updatePerson(p);
        
        return Response.status(Response.Status.OK).entity(JSONConverter.PersonToJSON(p)).build();
    }
}
