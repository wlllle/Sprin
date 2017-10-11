/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Impl;

import DAO.PersonDAO;
import stuff.Person;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import org.hibernate.Session;
import org.hibernate.Query;
import util.HibernateUtil;

/**
 *
 * @author slowen
 */
public class PersonDAOImpl implements PersonDAO{
    public void addPerson(Person person) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(person);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    public void updatePerson(Person person) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(person);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    public Person getPersonByID(Integer person_id) throws SQLException {
        Session session = null;
        ArrayList<Person> persons = new ArrayList<>();
        persons.ensureCapacity(1);
        Person person = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Person where ID = :personID ").setInteger("personID", person_id);
            persons = (ArrayList<Person>)query.list();
            if (!persons.isEmpty()) {
                person = persons.get(0);
            }
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return person;
    }
    
    public Collection getAllPersons() throws SQLException {
        Session session = null;
        ArrayList<Person> persons = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            persons = (ArrayList<Person>)session.createCriteria(Person.class).list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return persons;
    }
    
    public void deletePerson(Person person) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(person);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
