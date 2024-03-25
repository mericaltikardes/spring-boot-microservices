package com.mericaltikardes.employeeservice.service.Impl;

import com.mericaltikardes.employeeservice.dto.EmployeeDto;
import com.mericaltikardes.employeeservice.entity.Employee;
import com.mericaltikardes.employeeservice.exception.EmailAlreadyExistException;
import com.mericaltikardes.employeeservice.exception.ResourceNotFoundException;
import com.mericaltikardes.employeeservice.mapper.EmployeeMapper;
import com.mericaltikardes.employeeservice.repository.EmployeeRepository;
import com.mericaltikardes.employeeservice.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        if(employeeRepository.findByEmail(employeeDto.getEmail()).isPresent()){
            throw new EmailAlreadyExistException("Email Already Exist");
        }
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        //Add throw after
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User","id",id)
        );
        return EmployeeMapper.mapToEmployeeDto(employee);
    }
}
