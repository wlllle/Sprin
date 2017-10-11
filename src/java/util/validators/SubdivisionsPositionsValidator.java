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
import stuff.SubdivisionsPositions;

/**
 *
 * @author slowen
 */
@Component
public class SubdivisionsPositionsValidator implements Validator {
    public boolean supports(Class clazz) {
        return SubdivisionsPositions.class.isAssignableFrom(clazz);
    }
    
    public void validate(Object target, Errors errors) 
    {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "NumberOfPositions",
                "error.NumberOfPositions", "NumberOfPositions is required.");
    }
}
