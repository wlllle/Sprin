/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stuff;

import java.util.Date;

/**
 *
 * @author slowen
 */

public class PositionsHistory {
    
    private Integer ID;
    private Person Person;
    private SubdivisionsPositions SubdivisionsPositions;
    private Date ApprointmentDate;
    private Date OusterDate;
    
    public PositionsHistory() {
    }
    
    public void setID(Integer ID) {
        this.ID = ID;
    }
    
    public void setPerson(Person Person) {
        this.Person = Person;
    }
    
    public void setSubdivisionsPositions(SubdivisionsPositions SubdivisionsPositions) {
        this.SubdivisionsPositions = SubdivisionsPositions;
    }
    
    public void setApprointmentDate(Date ApprointmentDate) {
        this.ApprointmentDate = ApprointmentDate;
    }
    
    public void setOusterDate(Date OusterDate) {
        this.OusterDate = OusterDate;
    }
    
    public Integer getID() {
        return ID;
    }
    
    public Person getPerson() {
        return Person;
    }
    
    public SubdivisionsPositions getSubdivisionsPositions() {
        return SubdivisionsPositions;
    }
    
    public Date getApprointmentDate() {
        return ApprointmentDate;
    }
    
    public Date getOusterDate() {
        return OusterDate;
    }
}
