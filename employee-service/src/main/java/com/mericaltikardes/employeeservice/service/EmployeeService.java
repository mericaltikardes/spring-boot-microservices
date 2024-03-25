package com.mericaltikardes.employeeservice.service;

import com.mericaltikardes.employeeservice.dto.APIResponseDto;
import com.mericaltikardes.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long id);
}
