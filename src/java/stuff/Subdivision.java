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
public class Subdivision {
    
    private Integer ID;
    private String Name;
    private Person HeadPerson;
    private Integer HeadDivisionID;
    private Set SubdivisionPositions = new HashSet();
    
    public Subdivision() {
    }
    
    public void setID(Integer ID) {
        this.ID = ID;
    }
    
    public void setName(String Name) {
        this.Name = Name;
    }
    
    public void setHeadPerson(Person HeadPerson) {
        this.HeadPerson = HeadPerson;
    }
    
    public void setHeadDivisionID(Integer HeadDivisionID) {
        this.HeadDivisionID = HeadDivisionID;
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
    
    public Person getHeadPerson() {
        return HeadPerson;
    }
    
    public Integer getHeadDivisionID() {
        return HeadDivisionID;
    }
    
    public Set getSubdivisionPositions() {
        return SubdivisionPositions;
    }
}
