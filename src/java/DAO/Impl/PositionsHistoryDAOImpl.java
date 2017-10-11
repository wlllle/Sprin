/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Impl;

import DAO.PositionsHistoryDAO;
import stuff.PositionsHistory;

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
public class PositionsHistoryDAOImpl implements PositionsHistoryDAO{
    public void addPositionsHistory(PositionsHistory positions_history) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(positions_history);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    public void updatePositionsHistory(PositionsHistory positions_history) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(positions_history);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    public PositionsHistory getPositionsHistoryByID(Integer positions_history_id) throws SQLException {
        Session session = null;
        ArrayList<PositionsHistory> positions_history_list = new ArrayList<>();
        positions_history_list.ensureCapacity(1);
        PositionsHistory positions_history = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from PositionsHistory where ID = :positionsHistoryID ").setInteger("positionsHistoryID", positions_history_id);
            positions_history_list = (ArrayList<PositionsHistory>)query.list();
            if (!positions_history_list.isEmpty()) {
                positions_history = positions_history_list.get(0);
            }
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return positions_history;
    }
    
    public Collection getAllPositionsHistory() throws SQLException {
        Session session = null;
        ArrayList<PositionsHistory> positions_history = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            positions_history = (ArrayList<PositionsHistory>)session.createCriteria(PositionsHistory.class).list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return positions_history;
    }
    
    public void deletePositionsHistory(PositionsHistory positions_history) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(positions_history);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
