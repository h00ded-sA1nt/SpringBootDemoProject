package com.avidavi.springboot.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.avidavi.springboot.demo.dto.EmployeeDTO;
import com.avidavi.springboot.demo.services.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(path = "employee")
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
		return ResponseEntity.ok(employeeService.getAllEmployees());
	}

	@GetMapping("/{employeeId}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId) {
		return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
	}

	@PostMapping
	public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
		EmployeeDTO savedEmployee = employeeService.createEmployee(employeeDTO);
		return new ResponseEntity<EmployeeDTO>(savedEmployee, HttpStatus.CREATED);
	}

	@PutMapping("/{employeeId}")
	public ResponseEntity<EmployeeDTO> updateEmployeeById(@PathVariable Long employeeId,
			@RequestBody @Valid EmployeeDTO employeeDTO) {
		return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId, employeeDTO));
	}

	@PatchMapping("/{employeeId}")
	public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@PathVariable Long employeeId,
			Map<String, Object> updates) {
		// here @Valid cannot be applied so manual validation logic has to be written
		return ResponseEntity.ok(employeeService.updatePartialEmployeeById(employeeId, updates));
	}

	@DeleteMapping("/{employeeId}")
	public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId) {
		return ResponseEntity.ok(employeeService.deleteEmployeeById(employeeId));
	}
}
