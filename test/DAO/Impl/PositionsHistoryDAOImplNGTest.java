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
import stuff.*;

/**
 *
 * @author slowen
 */
public class PositionsHistoryDAOImplNGTest {
    
    public PositionsHistoryDAOImplNGTest() {
    }

    private boolean equalSubdivisionsPositions(SubdivisionsPositions a, SubdivisionsPositions b) {
        if ((a == null) && (b == null)) {
            return true;
        }
        if ((a == null) || (b == null)) {
            return false;
        }
        Assert.assertEquals(a.getID(), b.getID());
        Assert.assertEquals(a.getSubdivision().getID(), b.getSubdivision().getID());
        Assert.assertEquals(a.getPosition().getID(), b.getPosition().getID());
        Assert.assertEquals(a.getNumberOfPositions(), b.getNumberOfPositions());
        return true;
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
    public Object[][] positionsHistoryTestData() {
        return new Object[][] {
                new Object[] {1, 1, new Date(116, 11, 11), new Date(116, 11, 31)},
                new Object[] {1, 1, new Date(116, 11, 11), null},
        };
    }
    
    @Test(dataProvider = "positionsHistoryTestData")
    public void testPositionsHistory (Integer personID, Integer subdivisionsPositionsID, Date approintmentDate, Date ousterDate) throws SQLException {
        Collection positions_histories = Factory.getInstance().getPositionsHistoryDAO().getAllPositionsHistory();
        Integer size = positions_histories.size();
        Assert.assertNotNull(size);
        //creating new PositionsHistory
        PositionsHistory pos_hist = new PositionsHistory();
        pos_hist.setPerson(Factory.getInstance().getPersonDAO().getPersonByID(personID));
        pos_hist.setSubdivisionsPositions(Factory.getInstance().getSubdivisionsPositionsDAO().getSubdivisionsPositionsByID(subdivisionsPositionsID));
        pos_hist.setApprointmentDate(approintmentDate);
        pos_hist.setOusterDate(ousterDate);
        //adding it to DB
        Factory.getInstance().getPositionsHistoryDAO().addPositionsHistory(pos_hist);
        Assert.assertEquals(Factory.getInstance().getPositionsHistoryDAO().getAllPositionsHistory().size(), size + 1);
        //checking success of this op
        PositionsHistory tmp = Factory.getInstance().getPositionsHistoryDAO().getPositionsHistoryByID(pos_hist.getID());
        Assert.assertNotNull(tmp);
        Assert.assertEquals(tmp.getID(), pos_hist.getID());
        Assert.assertTrue(equalSubdivisionsPositions(tmp.getSubdivisionsPositions(), pos_hist.getSubdivisionsPositions()));
        Collection one = tmp.getSubdivisionsPositions().getPositionsHistory();
        Collection two = pos_hist.getSubdivisionsPositions().getPositionsHistory();
        Assert.assertTrue((one.isEmpty() == two.isEmpty()) || (one.size() == two.size()));
        Assert.assertTrue(equalPersons(tmp.getPerson(), pos_hist.getPerson()));
        one = tmp.getPerson().getPositionsHistory();
        two = pos_hist.getPerson().getPositionsHistory();
        Assert.assertTrue((one.isEmpty() == two.isEmpty()) || (one.size() == two.size()));
        Assert.assertEquals(tmp.getApprointmentDate(), pos_hist.getApprointmentDate());
        Assert.assertEquals(tmp.getOusterDate(), pos_hist.getOusterDate());
        //changing SubdivisionsPositions obj
        pos_hist.setOusterDate(new Date(117, 3, 6));
        Factory.getInstance().getPositionsHistoryDAO().updatePositionsHistory(pos_hist);
        //checking success of this op
        Assert.assertEquals(tmp.getID(), pos_hist.getID()); // ID shouldn't be changed
        tmp = Factory.getInstance().getPositionsHistoryDAO().getPositionsHistoryByID(pos_hist.getID());
        Assert.assertNotNull(tmp);
        Assert.assertEquals(tmp.getID(), pos_hist.getID());
        Assert.assertEquals(tmp.getOusterDate(), pos_hist.getOusterDate());
        //deleting this obj from DB
        Factory.getInstance().getPositionsHistoryDAO().deletePositionsHistory(tmp);
        Assert.assertEquals(Factory.getInstance().getPositionsHistoryDAO().getAllPositionsHistory().size(), size.intValue());
    }
}
