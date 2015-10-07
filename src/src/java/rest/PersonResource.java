/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import entity.Hobby;
import entity.Person;
import facade.PersonFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

    private PersonFacade facade;
    private Gson gson;

    /**
     * Creates a new instance of PersonResource
     */
    public PersonResource() {
        facade = new PersonFacade(Persistence.createEntityManagerFactory("3.semCa.3PU"));
        gson = new Gson();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("complete/{id}")
    public Response getPersonComplete(@PathParam("id") int id) {
        Person p = facade.getPerson(id);

        if (p == null) {
            throw new NullPointerException();
        }
        
        return Response.ok(JSONConverter.PersonToJSON(p)).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("contactinfo/{id}")
    public Response getPersonContactInfo(@PathParam("id") int id) {
        Person p = facade.getPerson(id);

        if (p == null) {
            throw new NullPointerException();
        }

        ContactInfoPerson cp = new ContactInfoPerson(p.getFirstName(), p.getLastName(), p.getPhones(), p.getEmail());
        
        return Response.ok(JSONConverter.ContactInfoPersonToJSON(cp)).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("complete")
    public Response getPersonsComplete() {
        List<Person> p = facade.getPersons();
        
        if(p.isEmpty()){
            throw new NullPointerException();
        }
        
        return Response.ok(JSONConverter.PersonToJSON(p)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("contactinfo")
    public Response getPersonsContactInfo() {
        List<Person> persons = facade.getPersons();
        
        if(persons.isEmpty()){
            throw new NullPointerException();
        }
        
        List<ContactInfoPerson> cPersons = new ArrayList();

        for (Person p : persons) {
            cPersons.add(new ContactInfoPerson(p.getFirstName(), p.getLastName(), p.getPhones(), p.getEmail()));
        }
       

        return Response.ok(JSONConverter.ContactInfoPersonToJSON(cPersons)).build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(String json){
        Person p = gson.fromJson(json, Person.class);
        
        if(p.getFirstName().isEmpty() || p.getLastName().isEmpty() || p.getEmail().isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        
        p = facade.addPerson(p);
        
        return Response.status(Response.Status.CREATED).entity(JSONConverter.PersonToJSON(p)).build();
    }
}
