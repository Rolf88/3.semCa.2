/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Company;
import entity.Person;
import infrastructure.IPersonFacade;
import java.io.Closeable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author RolfMoikjær
 */
public class PersonFacade implements Closeable, IPersonFacade {

    private final EntityManager entityManager;

    public PersonFacade(EntityManagerFactory factory) {
        this.entityManager = factory.createEntityManager();
    }

    @Override
    public Person getPerson(int id) {
        return this.entityManager.find(Person.class, (long) id);
    }

    @Override
    public List<Person> getPersons() {
        Query createQuery = this.entityManager.createQuery("SELECT p FROM Person p");

        return createQuery.getResultList();
    }

    @Override
    public List<Person> getPersons(String zipCode) {
        Query query = this.entityManager.createQuery("SELECT p FROM Person p WHERE p.address.city.zip = :zipCode");
        query.setParameter("zipCode", zipCode);

        return query.getResultList();

    }

    @Override
    public List<Person> findPersonsWithHobby(String hobbyName) {
        Query query = this.entityManager.createQuery("SELECT p FROM Person p INNER JOIN p.hobbies hob WHERE hob.name = :hobbyName");
        query.setParameter("hobbyName", hobbyName);

        return query.getResultList();
    }

    @Override
    public Company getCompany(String cvr) {
        Query query = this.entityManager.createQuery("SELECT c FROM Company c WHERE c.cvr = :cvr");
        query.setParameter("cvr", cvr);

        return (Company) query.getSingleResult();
    }

    @Override
    public Person addPerson(Person person) {
        if (person == null) {
            throw new NullPointerException("person cannot be null");
        }

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(person);
        this.entityManager.getTransaction().commit();

        return person;
    }

    @Override
    public void deletePerson(Person person) {
        if (person == null) {
            throw new NullPointerException("person cannot be null");
        }

        this.entityManager.getTransaction().begin();
        this.entityManager.remove(person);
        this.entityManager.getTransaction().commit();
    }

    @Override
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

    @Override
    public Person updatePerson(Person person) {
        if (person == null) {
            throw new NullPointerException("person");
        }

        this.entityManager.getTransaction().begin();
        this.entityManager.merge(person);
        this.entityManager.getTransaction().commit();

        return person;
    }
}
