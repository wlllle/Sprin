/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stuff;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author slowen
 */

public class SubdivisionsPositions {
    
    private Integer ID;
    private Subdivision Subdivision;
    private Position Position;
    private Integer NumberOfPositions;
    private Set PositionsHistory = new HashSet();
    
    public SubdivisionsPositions() {
    }
    
    public void setID(Integer ID) {
        this.ID = ID;
    }
    
    public void setSubdivision(Subdivision Subdivision) {
        this.Subdivision = Subdivision;
    }
    
    public void setPosition(Position Position) {
        this.Position = Position;
    }
    
    public void setNumberOfPositions(Integer NumberOfPositions) {
        this.NumberOfPositions = NumberOfPositions;
    }
    
    public void setPositionsHistory(Set PositionsHistory) {
        this.PositionsHistory = PositionsHistory;
    }
    
    public Integer getID() {
        return ID;
    }
    
    public Subdivision getSubdivision() {
        return Subdivision;
    }
    
    public Position getPosition() {
        return Position;
    }
    
    public Integer getNumberOfPositions() {
        return NumberOfPositions;
    }
    
    public Set getPositionsHistory() {
        return PositionsHistory;
    }
}
