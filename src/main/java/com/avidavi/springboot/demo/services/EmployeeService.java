package com.avidavi.springboot.demo.services;

import java.util.List;
import java.util.Map;

import com.avidavi.springboot.demo.dto.EmployeeDTO;
import com.avidavi.springboot.demo.exceptions.ResourceNotFoundException;

public interface EmployeeService {

	public List<EmployeeDTO> getAllEmployees();

	public EmployeeDTO getEmployeeById(Long employeeId) throws ResourceNotFoundException;

	public EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

	public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO);

	public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates);

	public boolean deleteEmployeeById(Long employeeId);

}
