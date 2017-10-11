/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import stuff.Position;
import stuff.SubdivisionsPositions;

import java.util.Collection;
import java.sql.SQLException;

/**
 *
 * @author slowen
 */
public interface PositionDAO {
    public void addPosition(Position position) throws SQLException;
    public void updatePosition(Position position) throws SQLException;
    public Position getPositionByID(Integer position_id) throws SQLException;
    public Collection getAllPositions() throws SQLException;
    public void deletePosition(Position position) throws SQLException;
}
