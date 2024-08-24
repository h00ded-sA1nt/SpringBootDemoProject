package com.avidavi.springboot.demo.configs;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.avidavi.springboot.demo.dto.EmployeeDTO;
import com.avidavi.springboot.demo.entities.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    // use this if you dont want to create this mapper as spring bean,
    // if you want to use this as spring bean use componentModel = "spring"
    // EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDTO toDto(Employee employee);

    Employee toEntity(EmployeeDTO employeeDTO);

}
