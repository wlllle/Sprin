/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import stuff.Person;
import stuff.Subdivision;
import stuff.SubdivisionsPositions;

import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author slowen
 */
public interface SubdivisionDAO {
    public void addSubdivision(Subdivision subdivision) throws SQLException;
    public void updateSubdivision(Subdivision subdivision) throws SQLException;
    public Subdivision getSubdivisionByID(Integer subdivision_id) throws SQLException;
    public Collection getAllSubdivisions() throws SQLException;
    public Collection getSubdivisionsByHeadDivisionID(Integer headdivision_id) throws SQLException;
    public void deleteSubdivision(Subdivision subdivision) throws SQLException;
}
