/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure;

import entity.Company;
import entity.Person;
import java.util.List;

/**
 *
 * @author priva_000
 */
public interface IPersonFacade {

    Company addCompany(Company company);

    Person addPerson(Person person);

    void deletePerson(Person person);

    List<Person> findPersonsWithHobby(String hobbyName);

    Company getCompany(String cvr);

    Person getPerson(int id);

    List<Person> getPersons();

    List<Person> getPersons(String zipCode);
    
    Person updatePerson(Person person);
}
