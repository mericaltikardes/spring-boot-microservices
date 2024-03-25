package com.mericaltikardes.employeeservice.service.Impl;

import com.mericaltikardes.employeeservice.dto.APIResponseDto;
import com.mericaltikardes.employeeservice.dto.DepartmentDto;
import com.mericaltikardes.employeeservice.dto.EmployeeDto;
import com.mericaltikardes.employeeservice.entity.Employee;
import com.mericaltikardes.employeeservice.exception.EmailAlreadyExistException;
import com.mericaltikardes.employeeservice.exception.ResourceNotFoundException;
import com.mericaltikardes.employeeservice.mapper.EmployeeMapper;
import com.mericaltikardes.employeeservice.repository.EmployeeRepository;
import com.mericaltikardes.employeeservice.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

   // private RestTemplate restTemplate;

    private WebClient webClient;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, WebClient webClient) {
        this.employeeRepository = employeeRepository;
        this.webClient = webClient;
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
    public APIResponseDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User","id",id)
        );
//        ResponseEntity<DepartmentDto> responseEntity =
//        restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
//                DepartmentDto.class);
        //DepartmentDto departmentDto = responseEntity.getBody();

        DepartmentDto departmentDto=webClient.get()
                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();
        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

        APIResponseDto apiResponseDto=new APIResponseDto(employeeDto,departmentDto);
        return apiResponseDto;
    }
}
