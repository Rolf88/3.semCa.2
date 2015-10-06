/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Company;
import static entity.Company_.cvr;
import entity.Person;
import java.io.Closeable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author RolfMoikjær
 */
public class PersonFacade implements Closeable {

    private final EntityManager entityManager;

    public PersonFacade(EntityManagerFactory factory) {
        this.entityManager = factory.createEntityManager();
    }

    public Person getPerson(int id) {
        Long longId = (long) id;
        return this.entityManager.find(Person.class, longId);
    }

    public List<Person> getPersons() {
        List persons;

        Query createQuery = this.entityManager.createQuery("SELECT p FROM Person p");

        persons = createQuery.getResultList();

        return persons;
    }

    public List<Person> getPersons(int zipCode) {
        List persons;
        
        Query createQuery = this.entityManager.createQuery("SELECT p.address.zip FROM Address INNER JOIN Person");
        
        persons = createQuery.getResultList();
        
        return persons;
        
    }

    public Company getCompany(long cvr) {
        Long longId = (long) cvr;
        return this.entityManager.find(Company.class, cvr);
    }

    @Override
    public void close() {
        this.entityManager.close();
    }

}
