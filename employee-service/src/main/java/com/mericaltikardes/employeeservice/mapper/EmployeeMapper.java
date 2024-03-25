package com.mericaltikardes.employeeservice.mapper;

import com.mericaltikardes.employeeservice.dto.EmployeeDto;
import com.mericaltikardes.employeeservice.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto(employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail());
        return employeeDto;
    }
    public static Employee mapToEmployee(EmployeeDto employeeDto){
        Employee employee = new Employee(employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail());
        return employee;
    }
}
