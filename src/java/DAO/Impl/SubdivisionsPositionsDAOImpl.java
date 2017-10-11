/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Impl;

import DAO.SubdivisionsPositionsDAO;
import stuff.SubdivisionsPositions;

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
public class SubdivisionsPositionsDAOImpl implements SubdivisionsPositionsDAO{
    public void addSubdivisionsPositions(SubdivisionsPositions subdivisions_positions) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(subdivisions_positions);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    public void updateSubdivisionsPositions(SubdivisionsPositions subdivisions_positions) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(subdivisions_positions);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    public SubdivisionsPositions getSubdivisionsPositionsByID(Integer subdivisions_positions_id) throws SQLException {
        Session session = null;
        ArrayList<SubdivisionsPositions> subdivisions_positions_list = new ArrayList<>();
        subdivisions_positions_list.ensureCapacity(1);
        SubdivisionsPositions subdivisions_positions = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from SubdivisionsPositions where ID = :subdivisionsPositionsID ").setInteger("subdivisionsPositionsID", subdivisions_positions_id);
            subdivisions_positions_list = (ArrayList<SubdivisionsPositions>)query.list();
            if (!subdivisions_positions_list.isEmpty()) {
                subdivisions_positions = subdivisions_positions_list.get(0);
            }
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return subdivisions_positions;
    }
    
    public Collection getAllSubdivisionsPositions() throws SQLException {
        Session session = null;
        ArrayList<SubdivisionsPositions> subdivisions_positions = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            subdivisions_positions = (ArrayList<SubdivisionsPositions>)session.createCriteria(SubdivisionsPositions.class).list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return subdivisions_positions;
    }
    
    public void deleteSubdivisionsPositions(SubdivisionsPositions subdivisions_positions) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(subdivisions_positions);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}