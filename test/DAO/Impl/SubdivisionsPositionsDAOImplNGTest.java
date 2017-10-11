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
public class SubdivisionsPositionsDAOImplNGTest {
    
    public SubdivisionsPositionsDAOImplNGTest() {
    }

    private boolean equalSubdivisions(Subdivision a, Subdivision b) {
        if ((a == null) && (b == null)) {
            return true;
        }
        if ((a == null) || (b == null)) {
            return false;
        }
        Assert.assertEquals(a.getID(), b.getID());
        Assert.assertEquals(a.getName(), b.getName());
        Assert.assertEquals(a.getHeadPerson().getID(), b.getHeadPerson().getID());
        Assert.assertEquals(a.getHeadDivisionID(), b.getHeadDivisionID());
        return true;
    }
    
    private boolean equalPositions(Position a, Position b) {
        if ((a == null) && (b == null)) {
            return true;
        }
        if ((a == null) || (b == null)) {
            return false;
        }
        Assert.assertEquals(a.getID(), b.getID());
        Assert.assertEquals(a.getName(), b.getName());
        Assert.assertEquals(a.getDuties(), b.getDuties());
        return true;
    }
    
    @DataProvider
    public Object[][] subdivisionsPositionsTestData() {
        return new Object[][] {
                new Object[] {2, 2, 2},
                /*new Object[] {2, 2, null},
                new Object[] {2, null, 2},
                new Object[] {2, null, null},
                new Object[] {null, 2, 2},
                new Object[] {null, 2, null},
                new Object[] {null, null, 2},
                new Object[] {null, null, null},*/
        };
    }
    
    @Test(dataProvider = "subdivisionsPositionsTestData")
    public void testSubdivisionsPositions (Integer subdivisionID, Integer positionID, Integer numberOfPositions) throws SQLException {
        Collection subdivisions_positions = Factory.getInstance().getSubdivisionsPositionsDAO().getAllSubdivisionsPositions();
        Integer size = subdivisions_positions.size();
        Assert.assertNotNull(size);
        //creating new SubdivisionsPositions
        SubdivisionsPositions sub_pos = new SubdivisionsPositions();
        sub_pos.setSubdivision(Factory.getInstance().getSubdivisionDAO().getSubdivisionByID(subdivisionID));
        sub_pos.setPosition(Factory.getInstance().getPositionDAO().getPositionByID(positionID));
        sub_pos.setNumberOfPositions(numberOfPositions);
        //adding it to DB
        Factory.getInstance().getSubdivisionsPositionsDAO().addSubdivisionsPositions(sub_pos);
        Assert.assertEquals(Factory.getInstance().getSubdivisionsPositionsDAO().getAllSubdivisionsPositions().size(), size + 1);
        //checking success of this op
        SubdivisionsPositions tmp = Factory.getInstance().getSubdivisionsPositionsDAO().getSubdivisionsPositionsByID(sub_pos.getID());
        Assert.assertNotNull(tmp);
        Assert.assertEquals(tmp.getID(), sub_pos.getID());
        Assert.assertTrue(equalSubdivisions(tmp.getSubdivision(), sub_pos.getSubdivision()));
        Collection one = tmp.getSubdivision().getSubdivisionPositions();
        Collection two = sub_pos.getSubdivision().getSubdivisionPositions();
        Assert.assertTrue((one.isEmpty() == two.isEmpty()) || (one.size() == two.size()));
        Assert.assertTrue(equalPositions(tmp.getPosition(), sub_pos.getPosition()));
        one = tmp.getPosition().getSubdivisionPositions();
        two = sub_pos.getPosition().getSubdivisionPositions();
        Assert.assertTrue((one.isEmpty() == two.isEmpty()) || (one.size() == two.size()));
        Assert.assertEquals(tmp.getNumberOfPositions(), sub_pos.getNumberOfPositions());
        //changing SubdivisionsPositions obj
        sub_pos.setNumberOfPositions(numberOfPositions + 1);
        Factory.getInstance().getSubdivisionsPositionsDAO().updateSubdivisionsPositions(sub_pos);
        //checking success of this op
        Assert.assertEquals(tmp.getID(), sub_pos.getID()); // ID shouldn't be changed
        tmp = Factory.getInstance().getSubdivisionsPositionsDAO().getSubdivisionsPositionsByID(sub_pos.getID());
        Assert.assertNotNull(tmp);
        Assert.assertEquals(tmp.getID(), sub_pos.getID());
        Assert.assertEquals(tmp.getNumberOfPositions(), sub_pos.getNumberOfPositions());
        //deleting this obj from DB
        Factory.getInstance().getSubdivisionsPositionsDAO().deleteSubdivisionsPositions(tmp);
        Assert.assertEquals(Factory.getInstance().getSubdivisionsPositionsDAO().getAllSubdivisionsPositions().size(), size.intValue());
    }
}
