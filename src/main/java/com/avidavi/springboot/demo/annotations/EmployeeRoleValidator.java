package com.avidavi.springboot.demo.annotations;

import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmployeeRoleValidator implements ConstraintValidator<EmplaoyeeRoleValidation, String> {

    @Override
    public boolean isValid(String inputRole, ConstraintValidatorContext context) {
        if (inputRole == null)
            return false;
        List<String> roles = List.of("USER", "ADMIN");
        return roles.contains(inputRole);
    }

}
