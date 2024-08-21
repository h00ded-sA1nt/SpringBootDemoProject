package com.avidavi.springboot.demo.services;

import java.util.List;

import com.avidavi.springboot.demo.dto.EmployeeDTO;
import com.avidavi.springboot.demo.exceptions.ResourceNotFoundException;

public interface EmployeeService {

	public List<EmployeeDTO> getAllEmployees();

	public EmployeeDTO getEmployeeById(Long id) throws ResourceNotFoundException;

}
