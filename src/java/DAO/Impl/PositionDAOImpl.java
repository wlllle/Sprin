/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package DAO.Impl;

import DAO.PositionDAO;
import stuff.Position;

import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Query;
import util.HibernateUtil;

/**
*
* @author slowen
*/

public class PositionDAOImpl implements PositionDAO{
    public void addPosition(Position position) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(position);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updatePosition(Position position) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(position);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    public Position getPositionByID(Integer position_id) throws SQLException {
        Session session = null;
        ArrayList<Position> positions = new ArrayList<>();
        positions.ensureCapacity(1);
        Position position = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Position where ID = :positionID ").setInteger("positionID", position_id);
            positions = (ArrayList<Position>)query.list();
            if (!positions.isEmpty()) {
                position = positions.get(0);
            }
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return position;
    }
    
    public Collection getAllPositions() throws SQLException {
        Session session = null;
        ArrayList<Position> positions = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            positions = (ArrayList<Position>)session.createCriteria(Position.class).list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return positions;
    }
    
    public void deletePosition(Position position) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(position);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}