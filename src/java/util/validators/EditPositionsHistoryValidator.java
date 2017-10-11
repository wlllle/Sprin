/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import stuff.PositionsHistory;

/**
 *
 * @author slowen
 */
@Component
public class EditPositionsHistoryValidator implements Validator {
    public boolean supports(Class clazz) {
        return PositionsHistory.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) 
    {
        PositionsHistory posHis = (PositionsHistory)target;
        if (posHis.getOusterDate() == null)
            errors.rejectValue("OusterDate", "error.OusterDate", "Ouster Date is required. Date format: dd/MM/yyyy");
    }
}
