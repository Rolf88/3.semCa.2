/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.Address;
import entity.CityInfo;
import entity.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author RolfMoikjær
 */
public class SchemaBuilder {

    public static void main(String[] args) {
        Persistence.generateSchema("3.semCa.2PU", null);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("3.semCa.2PU");
        EntityManager e = emf.createEntityManager();

        e.getTransaction().begin();
        Person person = new Person();
        person.setFirstName("Kim");
        person.setLastName("Larsen");

        Address address = new Address();
        address.setStreet("Test");
        address.setAdditionalInfo("add");

        CityInfo city = new CityInfo();
        city.setCity("Søborg");
        city.setZip("2860");

        address.setCity(city);
        person.setAddress(address);

        e.persist(person);
        e.getTransaction().commit();
    }
}
