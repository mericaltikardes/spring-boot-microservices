package com.mericaltikardes.employeeservice.controller;

import com.mericaltikardes.employeeservice.dto.EmployeeDto;
import com.mericaltikardes.employeeservice.entity.Employee;
import com.mericaltikardes.employeeservice.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee= employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable("id") Long EmployeeId){
        return employeeService.getEmployeeById(EmployeeId);
    }
}
