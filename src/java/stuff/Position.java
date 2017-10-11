/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stuff;

import java.util.Set;
import java.util.HashSet;

/**
 *
 * @author slowen
 */

public class Position {
    
    private Integer ID;
    private String Name;
    private String Duties;
    private Set SubdivisionPositions = new HashSet();

    public Position() {
    }
    
    public void setID(Integer ID) {
        this.ID = ID;
    }
    
    public void setName(String Name) {
        this.Name = Name;
    }
    
    public void setDuties(String Duties) {
        this.Duties = Duties;
    }
    
    public void setSubdivisionPositions(Set SubdivisionPositions) {
        this.SubdivisionPositions = SubdivisionPositions;
    }
    
    public Integer getID() {
        return ID;
    }
    
    public String getName() {
        return Name;
    }
    
    public String getDuties() {
        return Duties;
    }
    
    public Set getSubdivisionPositions() {
        return SubdivisionPositions;
    }
}
