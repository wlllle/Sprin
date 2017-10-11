/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Impl;

import hiber.Factory;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import stuff.Person;

/**
 *
 * @author slowen
 */
public class PersonDAOImplNGTest {
    
    public PersonDAOImplNGTest() {
    }

    @DataProvider
    public Object[][] personTestData() {
        return new Object[][] {
                new Object[] {"FirstName", "MiddleName", "LastName", "HomeAdress", "Education", new Date(96, 0, 1)},
                /*new Object[] {"FirstName", "MiddleName", "LastName", "HomeAdress", null, new Date(1996, 1, 1)},
                new Object[] {"FirstName", null, "LastName", "HomeAdress", null, new Date(1996, 1, 1)},
                new Object[] {null, null, null, null, null, new Date(1996, 1, 1)},
                new Object[] {"FirstName", "MiddleName", "LastName", "HomeAdress", "Education", null},*/
        };
    }
    
    @Test(dataProvider = "personTestData")
    public void testPerson (String firstName, String middleName, String lastName,
            String homeAdress, String education, Date recruitmentDate) throws SQLException {
        Collection persons = Factory.getInstance().getPersonDAO().getAllPersons();
        Integer size = persons.size();
        Assert.assertNotNull(size);
        //creating new Person
        Person pers = new Person();
        pers.setFirstName(firstName);
        pers.setMiddleName(middleName);
        pers.setLastName(lastName);
        pers.setHomeAdress(homeAdress);
        pers.setEducation(education);
        pers.setRecruitmentDate(recruitmentDate);
        //adding it to DB
        Factory.getInstance().getPersonDAO().addPerson(pers);
        Assert.assertEquals(Factory.getInstance().getPersonDAO().getAllPersons().size(), size + 1);
        //checking success of this op
        Person tmp = Factory.getInstance().getPersonDAO().getPersonByID(pers.getID());
        Assert.assertNotNull(tmp);
        Assert.assertEquals(tmp.getID(), pers.getID());
        Assert.assertEquals(tmp.getFirstName(), pers.getFirstName());
        Assert.assertEquals(tmp.getMiddleName(), pers.getMiddleName());
        Assert.assertEquals(tmp.getLastName(), pers.getLastName());        
        Assert.assertEquals(tmp.getHomeAdress(), pers.getHomeAdress());        
        Assert.assertEquals(tmp.getEducation(), pers.getEducation());
        Assert.assertEquals(tmp.getRecruitmentDate(), pers.getRecruitmentDate());
        //changing Person obj
        pers.setFirstName("New" + firstName);
        Factory.getInstance().getPersonDAO().updatePerson(pers);
        //checking success of this op
        Assert.assertEquals(tmp.getID(), pers.getID()); // ID shouldn't be changed
        tmp = Factory.getInstance().getPersonDAO().getPersonByID(pers.getID());
        Assert.assertNotNull(tmp);
        Assert.assertEquals(tmp.getID(), pers.getID());
        Assert.assertEquals(tmp.getFirstName(), pers.getFirstName());
        //deleting this obj from DB
        Factory.getInstance().getPersonDAO().deletePerson(tmp);
        Assert.assertEquals(Factory.getInstance().getPersonDAO().getAllPersons().size(), size.intValue());
    }
}
