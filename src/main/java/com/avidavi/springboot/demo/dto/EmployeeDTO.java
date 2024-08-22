package com.avidavi.springboot.demo.dto;

import java.time.LocalDate;

import com.avidavi.springboot.demo.annotations.EmplaoyeeRoleValidation;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

	private Long id;
	@NotBlank(message = "Name of employee cannot be blank.")
	@Size(min = 3, max = 20)
	private String name;
	@NotNull(message = "Salary of employee cannot be null.")
	@Positive(message = "Salary of employee should be positive.")
	@DecimalMin(value = "5000.00", message = "Salary of employee cannot be less than Rs 5000.00.")
	@DecimalMax(value = "5000000.00", message = "Salary of employee cannot be greater than Rs 5000000.00.")
	private Double salary;
	@NotNull(message = "Age of employee cannot be null.")
	@Min(value = 18, message = "Age of employee cannot be less than 18 yrs.")
	@Max(value = 60, message = "Age of employee cannot be more then 80 yrs.")
	private Integer age;
	@Past(message = "Employee date of joining should be past date.")
	@NotNull(message = "Employee date of joining cannot be null.")
	private LocalDate dateOfJoining;
	@NotBlank(message = "Email of employee cannot be blank.")
	@Email
	private String email;
	@NotBlank(message = "Role of employee cannot be blank.")
	// @Pattern(regexp = "^(USER|ADMIN)$", message = "Role of user should be either
	// Admin or User")
	@EmplaoyeeRoleValidation
	private String role;
	private boolean isActive;

}
