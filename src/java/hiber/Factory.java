/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiber;

import DAO.PersonDAO;
import DAO.PositionDAO;
import DAO.PositionsHistoryDAO;
import DAO.SubdivisionDAO;
import DAO.SubdivisionsPositionsDAO;

import DAO.Impl.PersonDAOImpl;
import DAO.Impl.PositionDAOImpl;
import DAO.Impl.PositionsHistoryDAOImpl;
import DAO.Impl.SubdivisionDAOImpl;
import DAO.Impl.SubdivisionsPositionsDAOImpl;

/**
 *
 * @author slowen
 */
public class Factory {
    private static PersonDAO personDAO = null;
    private static PositionDAO positionDAO = null;
    private static PositionsHistoryDAO positionsHistoryDAO = null;
    private static SubdivisionDAO subdivisionDAO = null;
    private static SubdivisionsPositionsDAO subdivisionsPositionsDAO = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public PersonDAO getPersonDAO(){
        if (personDAO == null){
            personDAO = new PersonDAOImpl();
        }
        return personDAO;
    }

    public PositionDAO getPositionDAO(){
        if (positionDAO == null){
            positionDAO = new PositionDAOImpl();
        }
        return positionDAO;
    }

    public PositionsHistoryDAO getPositionsHistoryDAO(){
        if (positionsHistoryDAO == null){
            positionsHistoryDAO = new PositionsHistoryDAOImpl();
        }
        return positionsHistoryDAO;
    }
    
    public SubdivisionDAO getSubdivisionDAO(){
        if (subdivisionDAO == null){
            subdivisionDAO = new SubdivisionDAOImpl();
        }
        return subdivisionDAO;
    }

    public SubdivisionsPositionsDAO getSubdivisionsPositionsDAO(){
        if (subdivisionsPositionsDAO == null){
            subdivisionsPositionsDAO = new SubdivisionsPositionsDAOImpl();
        }
        return subdivisionsPositionsDAO;
    }
}
