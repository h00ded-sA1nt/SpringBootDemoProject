package com.avidavi.springboot.demo.services.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import com.avidavi.springboot.demo.configs.EmployeeMapper;
import com.avidavi.springboot.demo.dto.EmployeeDTO;
import com.avidavi.springboot.demo.entities.Employee;
import com.avidavi.springboot.demo.exceptions.ResourceNotFoundException;
import com.avidavi.springboot.demo.repositories.EmployeeRepository;
import com.avidavi.springboot.demo.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final ModelMapper modelMapper;
	private final EmployeeMapper employeeMapper;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper,
			EmployeeMapper employeeMapper) {
		this.employeeRepository = employeeRepository;
		this.modelMapper = modelMapper;
		this.employeeMapper = employeeMapper;
	}

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();

		return employees
				.stream()
				.map(employee -> employeeMapper.toDto(employee))
				.collect(Collectors.toList());

	}

	@Override
	public EmployeeDTO getEmployeeById(Long employeeId) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id : " + employeeId));
		// return modelMapper.map(employee, EmployeeDTO.class);
		return employeeMapper.toDto(employee);
	}

	@Override
	public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
		Employee employee = modelMapper.map(employeeDTO, Employee.class);
		Employee savedEmployee = employeeRepository.save(employee);
		// return modelMapper.map(savedEmployee, EmployeeDTO.class);
		return employeeMapper.toDto(savedEmployee);
	}

	@Override
	public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
		isExistsByEmployeeId(employeeId);
		Employee employee = modelMapper.map(employeeDTO, Employee.class);
		employee.setId(employeeId);
		Employee savedEmployee = employeeRepository.save(employee);
		// return modelMapper.map(savedEmployee, EmployeeDTO.class);
		return employeeMapper.toDto(savedEmployee);
	}

	@Override
	public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
		isExistsByEmployeeId(employeeId);
		Employee employee = employeeRepository.findById(employeeId).get();
		updates.forEach((field, value) -> {
			Field fieldToBeUpdated = ReflectionUtils.findRequiredField(Employee.class, field);
			fieldToBeUpdated.setAccessible(true);
			ReflectionUtils.setField(fieldToBeUpdated, employee, value);
		});
		// return modelMapper.map(employeeRepository.save(employee), EmployeeDTO.class);
		return employeeMapper.toDto(employeeRepository.save(employee));
	}

	@Override
	public boolean deleteEmployeeById(Long employeeId) {
		isExistsByEmployeeId(employeeId);
		employeeRepository.deleteById(employeeId);
		return true;
	}

	private void isExistsByEmployeeId(Long employeeId) {
		boolean existsById = employeeRepository.existsById(employeeId);
		if (!existsById)
			throw new ResourceNotFoundException("Employee not found with id : " + employeeId);
	}

}
