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
import stuff.Person;

/**
 *
 * @author slowen
 */
@Component
public class PersonValidator implements Validator {
    public boolean supports(Class clazz) {
        return Person.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) 
    {
        Person person = (Person)target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "FirstName",
                "error.FirstName", "First Name is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "MiddleName",
                "error.MiddleName", "Middle Name is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "LastName",
                "error.LastName", "Last Name is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "HomeAdress",
                "error.HomeAdress", "Home Address is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Education",
                "error.Education", "Education is required.");
        if (person.getRecruitmentDate() == null)
            errors.rejectValue("RecruitmentDate", "error.RecruitmentDate", "Recruitment Date is required. Date format: dd/MM/yyyy");
    }
}