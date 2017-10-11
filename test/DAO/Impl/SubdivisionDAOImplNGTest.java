/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Impl;

import hiber.Factory;
import java.sql.SQLException;
import java.util.Collection;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import stuff.*;

/**
 *
 * @author slowen
 */
public class SubdivisionDAOImplNGTest {
    
    public SubdivisionDAOImplNGTest() {
    }
    
    private boolean equalPersons(Person a, Person b) {
        if ((a == null) && (b == null)) {
            return true;
        }
        if ((a == null) || (b == null)) {
            return false;
        }
        Assert.assertEquals(a.getID(), b.getID());
        Assert.assertEquals(a.getFirstName(), b.getFirstName());
        Assert.assertEquals(a.getMiddleName(), b.getMiddleName());
        Assert.assertEquals(a.getLastName(), b.getLastName());
        Assert.assertEquals(a.getEducation(), b.getEducation());
        Assert.assertEquals(a.getHomeAdress(), b.getHomeAdress());
        Assert.assertEquals(a.getRecruitmentDate(), b.getRecruitmentDate());
        return true;
    }
    
    @DataProvider
    public Object[][] subdivisionTestData() {
        return new Object[][] {
                new Object[] {"Name", 1, 1},
                new Object[] {"Name", 1, null},
                /*new Object[] {"Name", null, 1},
                new Object[] {"Name", null, null},
                new Object[] {null, 1, 1},
                new Object[] {null, 1, null},
                new Object[] {null, null, 1},
                new Object[] {null, null, null},*/
        };
    }
    
    @Test(dataProvider = "subdivisionTestData")
    public void testSubdivision (String name, Integer headPersonID, Integer headDivisionID) throws SQLException {
        Collection subdivisions = Factory.getInstance().getSubdivisionDAO().getAllSubdivisions();
        Integer size = subdivisions.size();
        Assert.assertNotNull(size);
        //creating new Subdivision
        Subdivision sub = new Subdivision();
        sub.setName(name);
        sub.setHeadPerson(Factory.getInstance().getPersonDAO().getPersonByID(headPersonID));
        sub.setHeadDivisionID(headDivisionID);
        //adding it to DB
        Factory.getInstance().getSubdivisionDAO().addSubdivision(sub);
        Assert.assertEquals(Factory.getInstance().getSubdivisionDAO().getAllSubdivisions().size(), size + 1);
        //checking success of this op
        Subdivision tmp = Factory.getInstance().getSubdivisionDAO().getSubdivisionByID(sub.getID());
        Assert.assertNotNull(tmp);
        Assert.assertEquals(tmp.getID(), sub.getID());
        Assert.assertEquals(tmp.getName(), sub.getName());
        Assert.assertTrue(equalPersons(tmp.getHeadPerson(), sub.getHeadPerson()));
        Collection one = tmp.getHeadPerson().getManagedSubdivisions();
        Collection two = sub.getHeadPerson().getManagedSubdivisions();
        Assert.assertTrue((one.isEmpty() == two.isEmpty()) || (one.size() == two.size()));
        Assert.assertEquals(tmp.getHeadDivisionID(), sub.getHeadDivisionID());
        //changing Subdivision obj
        sub.setName("New" + name);
        Factory.getInstance().getSubdivisionDAO().updateSubdivision(sub);
        //checking success of this op
        Assert.assertEquals(tmp.getID(), sub.getID()); // ID shouldn't be changed
        tmp = Factory.getInstance().getSubdivisionDAO().getSubdivisionByID(sub.getID());
        Assert.assertNotNull(tmp);
        Assert.assertEquals(tmp.getID(), sub.getID());
        Assert.assertEquals(tmp.getName(), sub.getName());
        //deleting this obj from DB
        Factory.getInstance().getSubdivisionDAO().deleteSubdivision(tmp);
        Assert.assertEquals(Factory.getInstance().getSubdivisionDAO().getAllSubdivisions().size(), size.intValue());
    }
    
}
