/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.converters;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import org.springframework.stereotype.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 *
 * @author slowen
 */
@Component
public class DateConverter extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) {
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = formatter.parse(text);
            this.setValue(date);
        } catch (ParseException e) {
            this.setValue(null);
        }
    }
    
    @Override
    public String getAsText() {
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String stringDate = formatter.format(this.getValue());
            return stringDate;
        } catch (Exception e) {
            return "day(dd)/month(MM)/year(yyyy)";
        }
    }
}