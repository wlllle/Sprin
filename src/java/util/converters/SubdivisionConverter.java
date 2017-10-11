package util.converters;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.springframework.stereotype.Component;
import hiber.Factory;
import java.beans.PropertyEditorSupport;
import stuff.*;

/**
 *
 * @author slowen
 */

@Component
public class SubdivisionConverter extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) {
        try {
            Subdivision c = Factory.getInstance().getSubdivisionDAO().getSubdivisionByID(Integer.valueOf(text));
            this.setValue(c);
        } catch(Exception e) { this.setValue(null); }
    }
}
