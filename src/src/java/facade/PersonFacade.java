/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Company;
import entity.Person;
import java.io.Closeable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
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
        return this.entityManager.find(Person.class, (long) id);
    }

    public List<Person> getPersons() {
        Query createQuery = this.entityManager.createQuery("SELECT p FROM Person p");

        return createQuery.getResultList();
    }

    public List<Person> getPersons(String zipCode) {
        Query query = this.entityManager.createQuery("SELECT p FROM Person p WHERE p.address.city.zip = :zipCode");
        query.setParameter("zipCode", zipCode);

        return query.getResultList();

    }

    public List<Person> findPersonsWithHobby(String hobbyName) {
        Query query = this.entityManager.createQuery("SELECT p FROM Person p INNER JOIN p.hobbies hob WHERE hob.name = :hobbyName");
        query.setParameter("hobbyName", hobbyName);

        return query.getResultList();
    }

    public Company getCompany(String cvr) {
        Query query = this.entityManager.createQuery("SELECT c FROM Company c WHERE c.cvr = :cvr");
        query.setParameter("cvr", cvr);

        return (Company) query.getSingleResult();
    }

    public Person addPerson(Person person) {
        if (person == null) {
            throw new NullPointerException("person cannot be null");
        }

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(person);
        this.entityManager.getTransaction().commit();

        return person;
    }
    
    public Company addCompany(Company company) {
        if (company == null) {
            throw new NullPointerException("company cannot be null");
        }

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(company);
        this.entityManager.getTransaction().commit();

        return company;
    }

    @Override
    public void close() {
        this.entityManager.close();
    }
}
