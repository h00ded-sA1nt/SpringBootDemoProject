package com.avidavi.springboot.demo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = { EmployeeRoleValidator.class })
public @interface EmplaoyeeRoleValidation {

    String message() default "Role of Employee can either be USER or ADMIN";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
