/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Impl;

import DAO.SubdivisionDAO;
import stuff.Subdivision;

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
public class SubdivisionDAOImpl implements SubdivisionDAO{
    public void addSubdivision(Subdivision subdivision) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(subdivision);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    public void updateSubdivision(Subdivision subdivision) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(subdivision);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    public Subdivision getSubdivisionByID(Integer subdivision_id) throws SQLException {
        Session session = null;
        ArrayList<Subdivision> subdivisions = new ArrayList<>();
        subdivisions.ensureCapacity(1);
        Subdivision subdivision = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Subdivision where ID = :subdivisionID").setInteger("subdivisionID", subdivision_id);
            subdivisions = (ArrayList<Subdivision>)query.list();
            if (!subdivisions.isEmpty()) {
                subdivision = subdivisions.get(0);
            }
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return subdivision;
    }
    
    public Collection getAllSubdivisions() throws SQLException {
        Session session = null;
        ArrayList<Subdivision> subdivisions = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            subdivisions = (ArrayList<Subdivision>)session.createCriteria(Subdivision.class).list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return subdivisions;
    }
    
    public Collection getSubdivisionsByHeadDivisionID(Integer headdivision_id) throws SQLException {
        Session session = null;
        ArrayList<Subdivision> subdivisions = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Subdivision where HeadDivisionID = :headDivisionID").setInteger("headDivisionID", headdivision_id);
            subdivisions = (ArrayList<Subdivision>)query.list();
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return subdivisions;
    }
    
    public void deleteSubdivision(Subdivision subdivision) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(subdivision);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
