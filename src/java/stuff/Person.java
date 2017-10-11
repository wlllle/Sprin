/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stuff;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author slowen
 */

public class Person {
    
    private Integer ID;
    private String FirstName;
    private String MiddleName;
    private String LastName;
    private String HomeAdress;
    private String Education;
    private Date RecruitmentDate;
    private Set PositionsHistory = new HashSet();
    private Set ManagedSubdivisions = new HashSet();

    public Person() {
    }
  
    public void setID(Integer ID) {
        this.ID = ID;
    }
    
    public void setFirstName(String Name) {
        this.FirstName = Name;
    }
    
    public void setMiddleName(String Name) {
        this.MiddleName = Name;
    }
    
    public void setLastName(String Name) {
        this.LastName = Name;
    }
    
    public void setHomeAdress(String Adress) {
        this.HomeAdress = Adress;
    }
    
    public void setEducation(String Education) {
        this.Education = Education;
    }
    
    public void setRecruitmentDate(Date RecruitmentDate) {
        this.RecruitmentDate = RecruitmentDate;
    }
    
    public void setPositionsHistory(Set PositionsHistory) {
        this.PositionsHistory = PositionsHistory;
    }
    
    public void setManagedSubdivisions(Set Subdivisions) {
        this.ManagedSubdivisions = Subdivisions;
    }
    
    public Integer getID() {
        return ID;
    }

    public String getFirstName() {
        return FirstName;
    }
            
    public String getMiddleName() {
        return MiddleName;
    }
    
    public String getLastName() {
        return LastName;
    }
            
    public String getHomeAdress() {
        return HomeAdress;
    }
    
    public String getEducation() {
        return Education;
    }
    
    public Date getRecruitmentDate() {
        return RecruitmentDate;
    }
    
    public Set getPositionsHistory() {
        return PositionsHistory;
    }
    
    public Set getManagedSubdivisions() {
        return ManagedSubdivisions;
    }
}
