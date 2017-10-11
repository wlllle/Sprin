/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import stuff.Person;
import stuff.Subdivision;
import stuff.PositionsHistory;

import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author slowen
 */
public interface PersonDAO {
    public void addPerson(Person person) throws SQLException;
    public void updatePerson(Person person) throws SQLException;
    public Person getPersonByID(Integer person_id) throws SQLException;
    public Collection getAllPersons() throws SQLException;
    public void deletePerson(Person person) throws SQLException;
}
