/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import stuff.PositionsHistory;
import stuff.Person;
import stuff.SubdivisionsPositions;

import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author slowen
 */
public interface PositionsHistoryDAO {
    public void addPositionsHistory(PositionsHistory positions_history) throws SQLException;
    public void updatePositionsHistory(PositionsHistory positions_history) throws SQLException;
    public PositionsHistory getPositionsHistoryByID(Integer positions_history_id) throws SQLException;
    public Collection getAllPositionsHistory() throws SQLException;
    public void deletePositionsHistory(PositionsHistory positions_history) throws SQLException;
}
