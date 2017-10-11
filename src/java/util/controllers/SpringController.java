/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.controllers;

import java.sql.SQLException;
import javax.annotation.Resource;
import stuff.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import util.validators.*;
import util.converters.*;
import util.service.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author slowen
 */

@Controller
public class SpringController {

    //---Services---//
    @Resource(name="service")
    private SpringService service;

    //---Validators---//
    private @Autowired
    SubdivisionValidator subValid;
    private @Autowired
    PersonValidator personValid;
    private @Autowired
    PositionValidator positionValid;
    private @Autowired
    SubdivisionsPositionsValidator subdivisionsPositionsValid;
    private @Autowired
    EditPositionsHistoryValidator editPositionsHistoryValid;
    private @Autowired
    AddPositionsHistoryValidator addPositionsHistoryValid;

    //---Converters---//
    private @Autowired 
    SubdivisionConverter subdivisionConverter;
    private @Autowired 
    PersonConverter personConverter;
    private @Autowired 
    DateConverter dateConverter;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Subdivision.class, this.subdivisionConverter);
        binder.registerCustomEditor(Person.class, this.personConverter);
        binder.registerCustomEditor(Date.class, this.dateConverter);
    }



    //---Main Page---//
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndex() throws SQLException {
        // This will resolve to /WEB-INF/jsp/mainpage.jsp
        return "indexpage";
    }



    //---Pages for Subdivisions---//
    @RequestMapping(value = "/subdivisions", method = RequestMethod.GET)
    public String getSubdivisions(Model model) throws SQLException {
        // Retrieve all subdivisions
        List<Subdivision> subdivisions = service.getAllSubdivisions();
        // Attach subdivisions to the Model
        model.addAttribute("subdivisions", subdivisions);
        // This will resolve to /WEB-INF/jsp/subdivisionspage.jsp
        return "subdivisionspage";
    }
    
    @RequestMapping(value = "/subdivisions/subinfo", method = RequestMethod.GET)
    public String getSubInfo(@RequestParam(value="id", required=true) Integer ID, 
              Model model) throws SQLException {
        // Retrieve existing Subdivision and add to model
        Subdivision sub = service.getSubdivisionByID(ID);
        model.addAttribute("subdivision", sub);
        // Also we need to retrieve head division...
        if (sub.getHeadDivisionID() != null) {
            model.addAttribute("headDivision", service.getSubdivisionByID(sub.getHeadDivisionID()));
        } else {
            model.addAttribute("headDivision", null);
        }
        // ... and subdivisions which have this subdivision as head division
        List<Subdivision> subdivisions = service.getSubdivisionsByHeadDivisionID(ID);
        model.addAttribute("subdivisions", subdivisions);
        // Now we are ready to retrieve cascade information.
        List<PositionsHistory> staff = service.getCurrentStaffBySubdivisionID(sub.getID());
        model.addAttribute("staff", staff);
        List<SubdivisionsPositions> freeVacancies = service.getFreeVacanciesBySubdivisionID(sub.getID());
        model.addAttribute("freeVacancies", freeVacancies);
        // This will resolve to /WEB-INF/jsp/subinfopage.jsp
        return "subinfopage";
    }
    
    @RequestMapping(value = "/subdivisions/add", method = RequestMethod.GET)
    public String getAddSubdivision(Model model) throws SQLException {
        // Create new Subdivision and add to model
        model.addAttribute("subdivisionAttribute", new Subdivision());
        // Retrieve all persons
        List<Person> persons = service.getAllPersons();
        model.addAttribute("persons", persons);
        List<Subdivision> subdivisions = service.getAllSubdivisions();
        model.addAttribute("subdivisions", subdivisions);
        model.addAttribute("discard", false);
        // This will resolve to /WEB-INF/jsp/addsubpage.jsp
        return "addsubpage";
    }
    
    @RequestMapping(value = "/subdivisions/add", method = RequestMethod.POST)
    public String addSubdivision(@ModelAttribute("subdivisionAttribute") Subdivision subdivision,
            @RequestParam String action, BindingResult result, Model model) throws SQLException {
        if(action.equals("Save")) {
            // "Save" action
            subValid.validate(subdivision, result);
            if (result.hasErrors()) {
                List<Person> persons = service.getAllPersons();
                model.addAttribute("persons", persons);
                List<Subdivision> subdivisions = service.getAllSubdivisions();
                model.addAttribute("subdivisions", subdivisions);
                model.addAttribute("discard", false);
                return "addsubpage";
            }
            // Do actual adding
            service.addSubdivision(subdivision);
            model.addAttribute("redirect", "subinfo");
            model.addAttribute("id", subdivision.getID());
            return "commitpage";
        } else if(action.equals("Discard")) {
            //"Discard" action
            List<Person> persons = service.getAllPersons();
            model.addAttribute("persons", persons);
            List<Subdivision> subdivisions = service.getAllSubdivisions();
            model.addAttribute("subdivisions", subdivisions);
            model.addAttribute("discard", true);
            return "addsubpage";
        } else if(action.equals("No, Cancel")) {
            List<Person> persons = service.getAllPersons();
            model.addAttribute("persons", persons);
            List<Subdivision> subdivisions = service.getAllSubdivisions();
            model.addAttribute("subdivisions", subdivisions);
            model.addAttribute("discard", false);
            return "addsubpage";
        }
        //"Yes, Discard" action
        model.addAttribute("redirect", "subdivisions");
        return "rollbackpage";
    }
    
    @RequestMapping(value = "/subdivisions/edit", method = RequestMethod.GET)
    public String getEditSubdivision(@RequestParam(value="id", required=true) Integer ID,
        Model model) throws SQLException {
        // Retrieve existing Subdivision and add to model
        model.addAttribute("subdivisionAttribute", service.getSubdivisionByID(ID));
        List<Person> persons = service.getAllPersons();
        model.addAttribute("persons", persons);
        List<Subdivision> subdivisions = service.getAllSubdivisions();
        model.addAttribute("subdivisions", subdivisions);
        model.addAttribute("discard", false);
        model.addAttribute("delete", false);
        // This will resolve to /WEB-INF/jsp/editsubpage.jsp
        return "editsubpage";
    }
    
    @RequestMapping(value = "/subdivisions/edit", method = RequestMethod.POST)
    public String editSubdivision(@ModelAttribute("subdivisionAttribute")
            Subdivision subdivision, @RequestParam String action,
            @RequestParam(value="id", required=true) Integer ID,
            BindingResult result, Model model) throws SQLException {
        subdivision.setID(ID);
        if(action.equals("Save")) {
            //"Save" action
            subValid.validate(subdivision, result);
            if (result.hasErrors()) {
                List<Person> persons = service.getAllPersons();
                model.addAttribute("persons", persons);
                List<Subdivision> subdivisions = service.getAllSubdivisions();
                model.addAttribute("subdivisions", subdivisions);
                model.addAttribute("discard", false);
                model.addAttribute("delete", false);
                return "editsubpage";
            }
            //Do actual editing
            service.updateSubdivision(subdivision);
            model.addAttribute("redirect", "subinfo");
            model.addAttribute("id", ID);
            return "commitpage";
        } else if(action.equals("Discard")) {
            //"Discard" action
            List<Person> persons = service.getAllPersons();
            model.addAttribute("persons", persons);
            List<Subdivision> subdivisions = service.getAllSubdivisions();
            model.addAttribute("subdivisions", subdivisions);
            model.addAttribute("discard", true);
            model.addAttribute("delete", false);
            return "editsubpage";
        } else if(action.equals("Delete")) {
            //"Delete" action
            List<Person> persons = service.getAllPersons();
            model.addAttribute("persons", persons);
            List<Subdivision> subdivisions = service.getAllSubdivisions();
            model.addAttribute("subdivisions", subdivisions);
            model.addAttribute("discard", false);
            model.addAttribute("delete", true);
            return "editsubpage";
        } else if(action.equals("No, Cancel")) {
            List<Person> persons = service.getAllPersons();
            model.addAttribute("persons", persons);
            List<Subdivision> subdivisions = service.getAllSubdivisions();
            model.addAttribute("subdivisions", subdivisions);
            model.addAttribute("discard", false);
            model.addAttribute("delete", false);
            return "editsubpage";
        } else if(action.equals("Yes, Discard")) {
            model.addAttribute("redirect", "subinfo");
            model.addAttribute("id", ID);
            return "rollbackpage";
        }
        //Actually delete subdivision
        if (!service.haveSubdivisions(subdivision)) {
            service.deleteSubdivision(subdivision);
            model.addAttribute("redirect", "subdivisions");
            return "commitpage";
        } else {
            model.addAttribute("redirect", "subinforeject");
            model.addAttribute("id", ID);
            return "rollbackpage";
        }
    }



    //---Pages for Persons---//
    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String getPersons(Model model) throws SQLException {
        // Retrieve all persons
        List<Person> persons = service.getAllPersons();
        // Attach persons to the Model
        model.addAttribute("persons", persons);
        // This will resolve to /WEB-INF/jsp/personspage.jsp
        return "personspage";
    }

    @RequestMapping(value = "/persons/personinfo", method = RequestMethod.GET)
    public String getPersonInfo(@RequestParam(value="id", required=true) Integer ID, 
              Model model) throws SQLException {
        // Retrieve existing Person and add to model
        Person person = service.getPersonByID(ID);
        model.addAttribute("person", person);
        List<Subdivision> managedSubs = new ArrayList<Subdivision>(person.getManagedSubdivisions());
        model.addAttribute("managedSubs", managedSubs);
        List<PositionsHistory> curPos= service.getAllCurPosByPersonID(ID);
        model.addAttribute("curPos", curPos);
        List<PositionsHistory> prevPos= service.getAllPrevPosByPersonID(ID);
        model.addAttribute("prevPos", prevPos);
        // This will resolve to /WEB-INF/jsp/personinfopage.jsp
        return "personinfopage";
    }

    @RequestMapping(value = "/persons/add", method = RequestMethod.GET)
    public String getAddPerson(Model model) throws SQLException {
        // Create new Person and add to model
        model.addAttribute("personAttribute", new Person());
        model.addAttribute("discard", false);
        // This will resolve to /WEB-INF/jsp/addpersonpage.jsp
        return "addpersonpage";
    }
    
    @RequestMapping(value = "/persons/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("personAttribute") Person person,
            @RequestParam String action, BindingResult result, Model model) throws SQLException {
        if(action.equals("Save")) {
            // "Save" action
            personValid.validate(person, result);
            if (result.hasErrors()) {
                model.addAttribute("discard", false);
                return "addpersonpage";
            }
            // Do actual adding
            service.addPerson(person);
            model.addAttribute("redirect", "personinfo");
            model.addAttribute("id", person.getID());
            return "commitpage";
        } else if(action.equals("Discard")) {
            //"Discard" action
            model.addAttribute("discard", true);
            return "addpersonpage";
        } else if(action.equals("No, Cancel")) {
            model.addAttribute("discard", false);
            return "addpersonpage";
        }
        //"Yes, Discard" action
        model.addAttribute("redirect", "persons");
        return "rollbackpage";
    }
    
    @RequestMapping(value = "/persons/edit", method = RequestMethod.GET)
    public String getEditPerson(@RequestParam(value="id", required=true) Integer ID,
        Model model) throws SQLException {
        // Retrieve existing Person and add to model
        model.addAttribute("personAttribute", service.getPersonByID(ID));
        model.addAttribute("discard", false);
        model.addAttribute("delete", false);
        // This will resolve to /WEB-INF/jsp/editpersonpage.jsp
        return "editpersonpage";
    }
    
    @RequestMapping(value = "/persons/edit", method = RequestMethod.POST)
    public String editPerson(@ModelAttribute("personAttribute")
            Person person, @RequestParam String action,
            @RequestParam(value="id", required=true) Integer ID,
            BindingResult result, Model model) throws SQLException {
        person.setID(ID);
        if(action.equals("Save")) {
            //"Save" action
            personValid.validate(person, result);
            if (result.hasErrors()) {
                model.addAttribute("discard", false);
                model.addAttribute("delete", false);
                return "editpersonpage";
            }
            //Do actual editing
            service.updatePerson(person);
            model.addAttribute("redirect", "personinfo");
            model.addAttribute("id", ID);
            return "commitpage";
        } else if(action.equals("Discard")) {
            //"Discard" action
            model.addAttribute("discard", true);
            model.addAttribute("delete", false);
            return "editpersonpage";
        } else if(action.equals("Delete")) {
            //"Delete" action
            model.addAttribute("discard", false);
            model.addAttribute("delete", true);
            return "editpersonpage";
        } else if(action.equals("No, Cancel")) {
            model.addAttribute("discard", false);
            model.addAttribute("delete", false);
            return "editpersonpage";
        } else if(action.equals("Yes, Discard")) {
            model.addAttribute("redirect", "personinfo");
            model.addAttribute("id", ID);
            return "rollbackpage";
        }
        //Actually delete person
        if (!service.headOfSubdivisions(person)) {
            service.deletePerson(person);
            model.addAttribute("redirect", "persons");
            return "commitpage";
        } else {
            model.addAttribute("redirect", "personinforeject");
            model.addAttribute("id", ID);
            return "rollbackpage";
        }
    }
    
    
    //---Pages for vacancies---//
    @RequestMapping(value = "/subdivisions/choosepos", method = RequestMethod.GET)
    public String getChoosePosition(@RequestParam(value="id", required=true) Integer ID,
            Model model) throws SQLException {
        // Retrieve all positions
        List<Position> positions = service.getAllPositions();
        model.addAttribute("positions", positions);
        model.addAttribute("id", ID);
        // This will resolve to /WEB-INF/jsp/choosepospage.jsp
        return "choosepospage";
    }
    
    @RequestMapping(value = "/subdivisions/addpos", method = RequestMethod.GET)
    public String getAddPosition(@RequestParam(value="id", required=true) Integer ID,
            Model model) throws SQLException {
        // Create new Position and add to model
        model.addAttribute("positionAttribute", new Position());
        model.addAttribute("discard", false);
        model.addAttribute("id", ID);
        // This will resolve to /WEB-INF/jsp/addvacancypage.jsp
        return "addpospage";
    }
    
    @RequestMapping(value = "/subdivisions/addpos", method = RequestMethod.POST)
    public String addPosition(@ModelAttribute("positionAttribute") Position position, 
            @RequestParam String action, @RequestParam(value="id", required=true) Integer ID,
            BindingResult result, Model model) throws SQLException {
        model.addAttribute("id", ID);
        if(action.equals("Save")) {
            // "Save" action
            positionValid.validate(position, result);
            if (result.hasErrors()) {
                model.addAttribute("discard", false);
                return "addpospage";
            }
            // Do actual adding
            service.addPosition(position);
            model.addAttribute("redirect", "choosenum");
            model.addAttribute("posid", position.getID());
            return "commitpage";
        } else if(action.equals("Discard")) {
            //"Discard" action
            model.addAttribute("discard", true);
            return "addpospage";
        } else if(action.equals("No, Cancel")) {
            model.addAttribute("discard", false);
            return "addpospage";
        }
        //"Yes, Discard" action
        model.addAttribute("redirect", "choosepos");
        return "rollbackpage";
    }
    
    @RequestMapping(value = "/subdivisions/choosenum", method = RequestMethod.GET)
    public String getChooseNum(@RequestParam(value="subid", required=true) Integer subID,
            @RequestParam(value="posid", required=true) Integer posID,
            Model model) throws SQLException {
        // Create new SubdivisionsPositions and add to model
        model.addAttribute("subposAttribute", new SubdivisionsPositions());
        model.addAttribute("discard", false);
        model.addAttribute("subid", subID);
        model.addAttribute("posid", posID);
        // This will resolve to /WEB-INF/jsp/choosenumpage.jsp
        return "choosenumpage";
    }
    
    @RequestMapping(value = "/subdivisions/choosenum", method = RequestMethod.POST)
    public String chooseNum(@ModelAttribute("subposAttribute")
            SubdivisionsPositions subdivisionsPositions, @RequestParam String action,
            @RequestParam(value="subid", required=true) Integer subID,
            @RequestParam(value="posid", required=true) Integer posID,
            BindingResult result, Model model) throws SQLException {
        model.addAttribute("subid", subID);
        model.addAttribute("posid", posID);
        if(action.equals("Save")) {
            // "Save" action
            subdivisionsPositionsValid.validate(subdivisionsPositions, result);
            if (result.hasErrors()) {
                model.addAttribute("discard", false);
                return "choosenumpage";
            }
            // Do actual adding
            subdivisionsPositions.setSubdivision(service.getSubdivisionByID(subID));
            subdivisionsPositions.setPosition(service.getPositionByID(posID));
            service.addSubdivisionsPositions(subdivisionsPositions);
            model.addAttribute("redirect", "subinfo");
            model.addAttribute("id", subID);
            return "commitpage";
        } else if(action.equals("Discard")) {
            //"Discard" action
            model.addAttribute("discard", true);
            return "choosenumpage";
        } else if(action.equals("No, Cancel")) {
            model.addAttribute("discard", false);
            return "choosenumpage";
        }
        //"Yes, Discard" action
        model.addAttribute("redirect", "choosepos");
        model.addAttribute("id", subID);
        return "rollbackpage";
    }
    
    @RequestMapping(value = "/subdivisions/editposhis", method = RequestMethod.GET)
    public String getEditPositionsHistory(@RequestParam(value="subid", required=true) Integer subID,
        @RequestParam(value="phid", required=true) Integer phID, Model model) throws SQLException {
        // Retrieve existing PositionsHistory and add to model
        model.addAttribute("positionsHistoryAttribute", service.getPositionsHistoryByID(phID));
        model.addAttribute("subid", subID);
        model.addAttribute("phid", phID);
        model.addAttribute("discard", false);
        // This will resolve to /WEB-INF/jsp/editpersonpage.jsp
        return "editposhispage";
    }
    
    @RequestMapping(value = "/subdivisions/editposhis", method = RequestMethod.POST)
    public String editPositionsHistory(@ModelAttribute("positionsHistoryAttribute")
            PositionsHistory positionsHistory, @RequestParam String action,
            @RequestParam(value="subid", required=true) Integer subID,
            @RequestParam(value="phid", required=true) Integer phID,
            BindingResult result, Model model) throws SQLException {
        positionsHistory.setID(phID);
        model.addAttribute("subid", subID);
        model.addAttribute("phid", phID);
        if(action.equals("Save")) {
            //"Save" action
            editPositionsHistoryValid.validate(positionsHistory, result);
            if (result.hasErrors()) {
                model.addAttribute("discard", false);
                return "editposhispage";
            }
            //Do actual editing
            service.updatePositionsHistory(positionsHistory);
            model.addAttribute("redirect", "subinfo");
            model.addAttribute("id", subID);
            return "commitpage";
        } else if(action.equals("Discard")) {
            //"Discard" action
            model.addAttribute("discard", true);
            return "editposhispage";
        } else if(action.equals("No, Cancel")) {
            model.addAttribute("discard", false);
            return "editposhispage";
        }
        //"Yes, Discard" action
        model.addAttribute("redirect", "subinfo");
        model.addAttribute("id", subID);
        return "rollbackpage";
    }
    
    @RequestMapping(value = "/subdivisions/addposhis", method = RequestMethod.GET)
    public String getAddPositionsHistory(@RequestParam(value="spid", required=true) 
            Integer spID, Model model) throws SQLException {
        // Create new PositionsHistory and add to model
        model.addAttribute("positionsHistoryAttribute", new PositionsHistory());
        // Retrieve all persons
        List<Person> persons = service.getAllPersons();
        model.addAttribute("persons", persons);
        model.addAttribute("spid", spID);
        model.addAttribute("discard", false);
        model.addAttribute("delete", false);
        // This will resolve to /WEB-INF/jsp/addsubpage.jsp
        return "addposhispage";
    }
    
    @RequestMapping(value = "/subdivisions/addposhis", method = RequestMethod.POST)
    public String addPositionsHistory(@ModelAttribute("positionsHistoryAttribute")
            PositionsHistory positionsHistory, @RequestParam String action,
            @RequestParam(value="spid", required=true) Integer spID, 
            BindingResult result, Model model) throws SQLException {
        model.addAttribute("spid", spID);
        if(action.equals("Save")) {
            //"Save" action
            addPositionsHistoryValid.validate(positionsHistory, result);
            if (result.hasErrors()) {
                List<Person> persons = service.getAllPersons();
                model.addAttribute("persons", persons);
                model.addAttribute("discard", false);
                model.addAttribute("delete", false);
                return "addposhispage";
            }
            //Do actual editing
            positionsHistory.setSubdivisionsPositions(service.getSubdivisionsPositionsByID(spID));
            positionsHistory.setOusterDate(null);
            service.addPositionsHistory(positionsHistory);
            model.addAttribute("redirect", "subinfo");
            model.addAttribute("id", positionsHistory.getSubdivisionsPositions().getSubdivision().getID());
            return "commitpage";
        } else if(action.equals("Discard")) {
            //"Discard" action
            List<Person> persons = service.getAllPersons();
            model.addAttribute("persons", persons);
            model.addAttribute("discard", true);
            model.addAttribute("delete", false);
            return "addposhispage";
        } else if(action.equals("Delete")) {
            //"Delete" action
            List<Person> persons = service.getAllPersons();
            model.addAttribute("persons", persons);
            model.addAttribute("discard", false);
            model.addAttribute("delete", true);
            return "addposhispage";
        } else if(action.equals("No, Cancel")) {
            List<Person> persons = service.getAllPersons();
            model.addAttribute("persons", persons);
            model.addAttribute("discard", false);
            model.addAttribute("delete", false);
            return "addposhispage";
        } else if(action.equals("Yes, Discard")) {
            model.addAttribute("redirect", "subinfo");
            model.addAttribute("id", service.getSubdivisionsPositionsByID(spID).getSubdivision().getID());
            return "rollbackpage";
        }
        //Actually delete SubdivisionsPositions
        model.addAttribute("redirect", "subinfo");
        model.addAttribute("id", service.getSubdivisionsPositionsByID(spID).getSubdivision().getID());
        service.deleteSubdivisionsPositions(service.getSubdivisionsPositionsByID(spID));
        return "commitpage";
    }
}