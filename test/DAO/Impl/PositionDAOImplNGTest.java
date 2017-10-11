/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Impl;

import hiber.Factory;
import java.sql.SQLException;
import java.util.Collection;
import stuff.Position;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.Assert;

/**
 *
 * @author slowen
 */
public class PositionDAOImplNGTest {
    
    public PositionDAOImplNGTest() {
    }

    @DataProvider
    public Object[][] positionTestData() {
        return new Object[][] {
                new Object[] {"Name", "Duties"},
                /*new Object[] {null, "Duties"},
                new Object[] {"Name", null},*/
        };
    }
    
    @Test(dataProvider = "positionTestData")
    public void testPosition (String name, String duties) throws SQLException {
        Collection positions = Factory.getInstance().getPositionDAO().getAllPositions();;
        Integer size = positions.size();
        Assert.assertNotNull(size);
        //creating new Position
        Position pos = new Position();
        pos.setName(name);
        pos.setDuties(duties);
        //adding it to DB
        Factory.getInstance().getPositionDAO().addPosition(pos);
        Assert.assertEquals(Factory.getInstance().getPositionDAO().getAllPositions().size(), size + 1);
        //checking success of this op
        Position tmp = Factory.getInstance().getPositionDAO().getPositionByID(pos.getID());
        Assert.assertNotNull(tmp);
        Assert.assertEquals(tmp.getID(), pos.getID());
        Assert.assertEquals(tmp.getName(), pos.getName());
        Assert.assertEquals(tmp.getDuties(), pos.getDuties());
        //changing Position obj
        pos.setName("New" + name);
        Factory.getInstance().getPositionDAO().updatePosition(pos);
        //checking success of this op
        Assert.assertEquals(tmp.getID(), pos.getID()); // ID shouldn't be changed
        tmp = Factory.getInstance().getPositionDAO().getPositionByID(pos.getID());
        Assert.assertNotNull(tmp);
        Assert.assertEquals(tmp.getID(), pos.getID());
        Assert.assertEquals(tmp.getName(), pos.getName());
        //deleting this obj from DB
        Factory.getInstance().getPositionDAO().deletePosition(tmp);
        Assert.assertEquals(Factory.getInstance().getPositionDAO().getAllPositions().size(), size.intValue());
    }
}