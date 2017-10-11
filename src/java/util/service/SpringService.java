/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.service;

import java.util.List;
 import java.util.ArrayList;
import hiber.Factory;
import stuff.*;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import org.springframework.stereotype.Service;

/**
 *
 * @author slowen
 */

@Service("service")
public class SpringService {
    //---Person Service---//
    public void addPerson(Person person) throws SQLException {
        Factory.getInstance().getPersonDAO().addPerson(person);
    }
    public void updatePerson(Person person) throws SQLException {
        Person source = getPersonByID(person.getID());
        source.setFirstName(person.getFirstName());
        source.setMiddleName(person.getMiddleName());
        source.setLastName(person.getLastName());
        source.setHomeAdress(person.getHomeAdress());
        source.setEducation(person.getEducation());
        source.setRecruitmentDate(person.getRecruitmentDate());
        Factory.getInstance().getPersonDAO().updatePerson(source);
    }
    public Person getPersonByID(Integer ID) throws SQLException {
        Person person = Factory.getInstance().getPersonDAO().getPersonByID(ID);
        return person;
    }
    public List<Person> getAllPersons() throws SQLException {
        List<Person> persons = (List<Person>)Factory.getInstance().getPersonDAO().getAllPersons();
        return persons;
    }
    public void deletePerson(Person person) throws SQLException {
        List<PositionsHistory> PosHis = getAllPosHisByPersonID(person.getID());
        Iterator iterator = PosHis.iterator();
        while (iterator.hasNext()) {
            PositionsHistory PH = (PositionsHistory) iterator.next();
            deletePositionsHistory(getPositionsHistoryByID(PH.getID()));
        }
        Person source = getPersonByID(person.getID());
        Factory.getInstance().getPersonDAO().deletePerson(source);
    }
    
    //---Subdivision Service---//
    public void addSubdivision(Subdivision subdivision) throws SQLException {
        Factory.getInstance().getSubdivisionDAO().addSubdivision(subdivision);
    }
    public void updateSubdivision(Subdivision subdivision) throws SQLException {
        Subdivision source = getSubdivisionByID(subdivision.getID());
        source.setName(subdivision.getName());
        source.setHeadPerson(subdivision.getHeadPerson());
        source.setHeadDivisionID(subdivision.getHeadDivisionID());
        Factory.getInstance().getSubdivisionDAO().updateSubdivision(source);
    }
    public Subdivision getSubdivisionByID(Integer ID) throws SQLException {
        Subdivision subdivision = Factory.getInstance().getSubdivisionDAO().getSubdivisionByID(ID);
        return subdivision;
    }
    public List<Subdivision> getAllSubdivisions() throws SQLException {
        List<Subdivision> subdivisions = (List<Subdivision>)Factory.getInstance().getSubdivisionDAO().getAllSubdivisions();
        return subdivisions;
    }
    public List<Subdivision> getSubdivisionsByHeadDivisionID(Integer ID) throws SQLException {
        List<Subdivision> subdivisions = (List<Subdivision>)Factory.getInstance().getSubdivisionDAO().getSubdivisionsByHeadDivisionID(ID);
        return subdivisions;
    }
    public void deleteSubdivision(Subdivision subdivision) throws SQLException {
        List<SubdivisionsPositions> SubPos = getAllSubPosBySubID(subdivision.getID());
        List<PositionsHistory> PosHis = getAllPosHisBySubdivisionID(subdivision.getID());
        Iterator iterator = PosHis.iterator();
        while (iterator.hasNext()) {
            PositionsHistory PH = (PositionsHistory) iterator.next();
            deletePositionsHistory(getPositionsHistoryByID(PH.getID()));
        }
        iterator = SubPos.iterator();
        while (iterator.hasNext()) {
            SubdivisionsPositions SP = (SubdivisionsPositions) iterator.next();
            deleteSubdivisionsPositions(getSubdivisionsPositionsByID(SP.getID()));
        }
        Subdivision source = getSubdivisionByID(subdivision.getID());
        Factory.getInstance().getSubdivisionDAO().deleteSubdivision(source);
    }
    
    //---SubdivisionsPositions Service---//
    public void addSubdivisionsPositions(SubdivisionsPositions subdivisions_positions) throws SQLException {
        List<SubdivisionsPositions> SubPos = getAllSubPosBySubID(subdivisions_positions.getSubdivision().getID());
        Iterator iteratorSP = SubPos.iterator();
        while (iteratorSP.hasNext()) {
            SubdivisionsPositions SP = (SubdivisionsPositions) iteratorSP.next();
            if (SP.getPosition().getID() != subdivisions_positions.getPosition().getID()) {
                iteratorSP.remove();
            }
        }
        if (SubPos.isEmpty()) {
            Factory.getInstance().getSubdivisionsPositionsDAO().addSubdivisionsPositions(subdivisions_positions);
        } else {
            SubdivisionsPositions subPos =
                    Factory.getInstance().getSubdivisionsPositionsDAO().getSubdivisionsPositionsByID(SubPos.get(0).getID());
            subPos.setNumberOfPositions(subPos.getNumberOfPositions() + subdivisions_positions.getNumberOfPositions());
            Factory.getInstance().getSubdivisionsPositionsDAO().updateSubdivisionsPositions(subPos);
        }
    }
    public void updateSubdivisionsPositions(SubdivisionsPositions subdivisions_positions) throws SQLException {
        Factory.getInstance().getSubdivisionsPositionsDAO().updateSubdivisionsPositions(subdivisions_positions);
    }
    public SubdivisionsPositions getSubdivisionsPositionsByID(Integer subdivisions_positions_id) throws SQLException {
        SubdivisionsPositions subdivisionPosition = 
                Factory.getInstance().getSubdivisionsPositionsDAO().getSubdivisionsPositionsByID(subdivisions_positions_id);
        return subdivisionPosition;
    }
    public List<SubdivisionsPositions> getAllSubdivisionsPositions() throws SQLException {
        List<SubdivisionsPositions> subdivisionsPositions = (List<SubdivisionsPositions>)
                Factory.getInstance().getSubdivisionsPositionsDAO().getAllSubdivisionsPositions();
        return subdivisionsPositions;
    }
    public void deleteSubdivisionsPositions(SubdivisionsPositions subdivisions_positions) throws SQLException {
        List<PositionsHistory> PosHis = getAllPosHisBySubPosID(subdivisions_positions.getID());
        Iterator iterator = PosHis.iterator();
        Integer counter = 0;
        while (iterator.hasNext()) {
            PositionsHistory PH = (PositionsHistory) iterator.next();
            if (PH.getOusterDate() == null)
                ++counter;
        }
        if (counter == 0) {
            iterator = PosHis.iterator();
            while (iterator.hasNext()) {
                PositionsHistory PH = (PositionsHistory) iterator.next();
                deletePositionsHistory(getPositionsHistoryByID(PH.getID()));
            }
            SubdivisionsPositions source = getSubdivisionsPositionsByID(subdivisions_positions.getID());
            Factory.getInstance().getSubdivisionsPositionsDAO().deleteSubdivisionsPositions(source);
        } else {
            SubdivisionsPositions source = getSubdivisionsPositionsByID(subdivisions_positions.getID());
            source.setNumberOfPositions(counter);
            Factory.getInstance().getSubdivisionsPositionsDAO().updateSubdivisionsPositions(source);
        }
    }
    
    //---PositionsHistory Service---//
    public void addPositionsHistory(PositionsHistory positions_history) throws SQLException {
        Factory.getInstance().getPositionsHistoryDAO().addPositionsHistory(positions_history);
    }
    public void updatePositionsHistory(PositionsHistory positions_history) throws SQLException {
        PositionsHistory source = getPositionsHistoryByID(positions_history.getID());
        source.setOusterDate(positions_history.getOusterDate());
        Factory.getInstance().getPositionsHistoryDAO().updatePositionsHistory(source);
    }
    public PositionsHistory getPositionsHistoryByID(Integer positions_history_id) throws SQLException {
        PositionsHistory positionsHistory = 
                Factory.getInstance().getPositionsHistoryDAO().getPositionsHistoryByID(positions_history_id);
        return positionsHistory;
    }
    public List<PositionsHistory> getAllPositionsHistory() throws SQLException {
        List<PositionsHistory> positionsHistory = (List<PositionsHistory>)
                Factory.getInstance().getPositionsHistoryDAO().getAllPositionsHistory();
        return positionsHistory;
    }
    public void deletePositionsHistory(PositionsHistory positions_history) throws SQLException {
        Factory.getInstance().getPositionsHistoryDAO().deletePositionsHistory(positions_history);
    }
    
    //---Positions Service---//
    public void addPosition(Position position) throws SQLException {
        Factory.getInstance().getPositionDAO().addPosition(position);
    }
    public List<Position> getAllPositions() throws SQLException {
        List<Position> positions = (List<Position>)Factory.getInstance().getPositionDAO().getAllPositions();
        Iterator iterator = positions.iterator();
        while (iterator.hasNext()) {
            Position position = (Position) iterator.next();
            if (position.getSubdivisionPositions().isEmpty()) {
                deletePosition(position);
                iterator.remove();
            }
        }
        return positions;
    }
    public Position getPositionByID(Integer position_id) throws SQLException {
        Position position = Factory.getInstance().getPositionDAO().getPositionByID(position_id);
        return position;
    }
    public void deletePosition(Position position) throws SQLException {
        Position source = Factory.getInstance().getPositionDAO().getPositionByID(position.getID());
        Factory.getInstance().getPositionDAO().deletePosition(source);
    }
    
    //---Custom Subdivision Service---//
    public Boolean haveSubdivisions(Subdivision subdivision) throws SQLException {
        Collection subdivisions = Factory.getInstance().getSubdivisionDAO().getAllSubdivisions();
        Iterator iterator = subdivisions.iterator();
        while (iterator.hasNext()) {
            Subdivision sub = (Subdivision) iterator.next();
            if (sub.getHeadDivisionID() == subdivision.getID()) {
                return true;
            }
        }
        return false;
    }
    
    public List<PositionsHistory> getAllPosHisBySubdivisionID(Integer ID) throws SQLException {
        Collection PosHis = Factory.getInstance().getPositionsHistoryDAO().getAllPositionsHistory();
        Iterator iterator = PosHis.iterator();
        while (iterator.hasNext()) {
            PositionsHistory vacancy = (PositionsHistory) iterator.next();
            if (vacancy.getSubdivisionsPositions().getSubdivision().getID() != ID) {
                iterator.remove();
            }
        }
        return (List<PositionsHistory>)PosHis;
    }
    
    public List<PositionsHistory> getAllPosHisBySubPosID(Integer ID) throws SQLException {
        Collection PosHis = Factory.getInstance().getPositionsHistoryDAO().getAllPositionsHistory();
        Iterator iterator = PosHis.iterator();
        while (iterator.hasNext()) {
            PositionsHistory vacancy = (PositionsHistory) iterator.next();
            if (vacancy.getSubdivisionsPositions().getID() != ID) {
                iterator.remove();
            }
        }
        return (List<PositionsHistory>)PosHis;
    }
    
    public List<PositionsHistory> getCurrentStaffBySubdivisionID(Integer ID) throws SQLException {
        Collection PosHis = Factory.getInstance().getPositionsHistoryDAO().getAllPositionsHistory();
        Iterator iterator = PosHis.iterator();
        while (iterator.hasNext()) {
            PositionsHistory vacancy = (PositionsHistory) iterator.next();
            if (vacancy.getOusterDate() != null) {
                iterator.remove();
            } else {
                SubdivisionsPositions subPos = vacancy.getSubdivisionsPositions();
                if (subPos.getSubdivision().getID() != ID) {
                    iterator.remove();
                }
            }
        }
        return (List<PositionsHistory>)PosHis;
    }
    
    public List<SubdivisionsPositions> getAllSubPosBySubID(Integer ID) throws SQLException {
        Collection SubPos = Factory.getInstance().getSubdivisionsPositionsDAO().getAllSubdivisionsPositions();
        Iterator iteratorSP = SubPos.iterator();
        while (iteratorSP.hasNext()) {
            SubdivisionsPositions SP = (SubdivisionsPositions) iteratorSP.next();
            if (SP.getSubdivision().getID() != ID) {
                iteratorSP.remove();
            }
        }
        return (List<SubdivisionsPositions>)SubPos;
    }
    
    public List<SubdivisionsPositions> getFreeVacanciesBySubdivisionID(Integer ID) throws SQLException {
        List<SubdivisionsPositions> SubPos = getAllSubPosBySubID(ID);
        List<PositionsHistory> PosHis = getCurrentStaffBySubdivisionID(ID);
        Iterator iteratorSP = SubPos.iterator();
        while (iteratorSP.hasNext()) {
            SubdivisionsPositions SP = (SubdivisionsPositions) iteratorSP.next();
            Iterator iteratorPH = PosHis.iterator();
            while (iteratorPH.hasNext()) {
                PositionsHistory PH = (PositionsHistory) iteratorPH.next();
                if (PH.getSubdivisionsPositions().getID() == SP.getID()) {
                    SP.setNumberOfPositions(SP.getNumberOfPositions() - 1);
                    iteratorPH.remove();
                }
            }
            if (SP.getNumberOfPositions() <= 0) { //Database allows < 0 I suppose, though it's wrong
                iteratorSP.remove();
            }
        }
        return SubPos;
    }
    
    //---Custom Person Service---//
    public Boolean headOfSubdivisions(Person person) throws SQLException {
        Collection subdivisions = Factory.getInstance().getSubdivisionDAO().getAllSubdivisions();
        Iterator iterator = subdivisions.iterator();
        while (iterator.hasNext()) {
            Subdivision sub = (Subdivision) iterator.next();
            if (sub.getHeadPerson().getID() == person.getID()) {
                return true;
            }
        }
        return false;
    }
    
    public List<PositionsHistory> getAllPosHisByPersonID(Integer ID) throws SQLException {
        Collection PosHis = Factory.getInstance().getPositionsHistoryDAO().getAllPositionsHistory();
        Iterator iterator = PosHis.iterator();
        while (iterator.hasNext()) {
            PositionsHistory vacancy = (PositionsHistory) iterator.next();
            if (vacancy.getPerson().getID() != ID) {
                iterator.remove();
            }
        }
        return (List<PositionsHistory>)PosHis;
    }
    
    public List<PositionsHistory> getAllCurPosByPersonID(Integer ID) throws SQLException {
        List<PositionsHistory> posHis = getAllPosHisByPersonID(ID);
        Iterator iterator = posHis.iterator();
        while (iterator.hasNext()) {
            PositionsHistory vacancy = (PositionsHistory) iterator.next();
            if (vacancy.getOusterDate() != null) {
                iterator.remove();
            }
        }
        return posHis;
    }
    
    public List<PositionsHistory> getAllPrevPosByPersonID(Integer ID) throws SQLException {
        List<PositionsHistory> posHis = getAllPosHisByPersonID(ID);
        Iterator iterator = posHis.iterator();
        while (iterator.hasNext()) {
            PositionsHistory vacancy = (PositionsHistory) iterator.next();
            if (vacancy.getOusterDate() == null) {
                iterator.remove();
            }
        }
        return posHis;
    }
}
