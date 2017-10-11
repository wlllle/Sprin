/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.converters;

import hiber.Factory;
import java.beans.PropertyEditorSupport;
import org.springframework.stereotype.Component;
import stuff.Person;

/**
 *
 * @author slowen
 */

@Component
public class PersonConverter extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) {
        try {
            Person person = Factory.getInstance().getPersonDAO().getPersonByID(Integer.valueOf(text));
            this.setValue(person);
        } catch(Exception e) {
            this.setValue(null);
        }
    }
    
    @Override
    public String getAsText() {
        Person person = (Person)this.getValue();
        if (person != null) {
            return Integer.toString(person.getID());
        }
        else {
            // Just in case
            return "";
        }
    }
}
