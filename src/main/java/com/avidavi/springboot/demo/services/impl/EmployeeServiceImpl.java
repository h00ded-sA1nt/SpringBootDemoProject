package com.avidavi.springboot.demo.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.avidavi.springboot.demo.dto.EmployeeDTO;
import com.avidavi.springboot.demo.entities.Employee;
import com.avidavi.springboot.demo.exceptions.ResourceNotFoundException;
import com.avidavi.springboot.demo.repositories.EmployeeRepository;
import com.avidavi.springboot.demo.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final ModelMapper modelMapper;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
		this.employeeRepository = employeeRepository;
		this.modelMapper = new ModelMapper();
	}

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		return employees
				.stream()
				.map(employee -> modelMapper.map(employee, EmployeeDTO.class))
				.collect(Collectors.toList());

	}

	@Override
	public EmployeeDTO getEmployeeById(Long id) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id : " + id));
		return modelMapper.map(employee, EmployeeDTO.class);
	}
}
