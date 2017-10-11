/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import stuff.Position;
import stuff.PositionsHistory;
import stuff.Subdivision;
import stuff.SubdivisionsPositions;

import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author slowen
 */
public interface SubdivisionsPositionsDAO {
    public void addSubdivisionsPositions(SubdivisionsPositions subdivisions_positions) throws SQLException;
    public void updateSubdivisionsPositions(SubdivisionsPositions subdivisions_positions) throws SQLException;
    public SubdivisionsPositions getSubdivisionsPositionsByID(Integer subdivisions_positions_id) throws SQLException;
    public Collection getAllSubdivisionsPositions() throws SQLException;
    public void deleteSubdivisionsPositions(SubdivisionsPositions subdivisions_positions) throws SQLException;
}
