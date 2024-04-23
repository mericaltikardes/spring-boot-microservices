package com.mericaltikardes.employeeservice.service.Impl;

import com.mericaltikardes.employeeservice.dto.APIResponseDto;
import com.mericaltikardes.employeeservice.dto.DepartmentDto;
import com.mericaltikardes.employeeservice.dto.EmployeeDto;
import com.mericaltikardes.employeeservice.dto.OrganizationDto;
import com.mericaltikardes.employeeservice.entity.Employee;
import com.mericaltikardes.employeeservice.exception.EmailAlreadyExistException;
import com.mericaltikardes.employeeservice.exception.ResourceNotFoundException;
import com.mericaltikardes.employeeservice.mapper.EmployeeMapper;
import com.mericaltikardes.employeeservice.repository.EmployeeRepository;
import com.mericaltikardes.employeeservice.service.DepartmentServiceAPIClient;
import com.mericaltikardes.employeeservice.service.EmployeeService;
import com.mericaltikardes.employeeservice.service.OrganizationServiceAPIClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

   // private RestTemplate restTemplate;

  //  private WebClient webClient;
    private DepartmentServiceAPIClient departmentServiceAPIClient;

    private OrganizationServiceAPIClient organizationServiceAPIClient;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentServiceAPIClient apiClient,
                               OrganizationServiceAPIClient organizationServiceAPIClient) {
        this.employeeRepository = employeeRepository;
        this.departmentServiceAPIClient = apiClient;
        this.organizationServiceAPIClient = organizationServiceAPIClient;
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
    @CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User","id",id)
        );
//        ResponseEntity<DepartmentDto> responseEntity =
//        restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
//                DepartmentDto.class);
        //DepartmentDto departmentDto = responseEntity.getBody();

//        DepartmentDto departmentDto=webClient.get()
//                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();
        DepartmentDto departmentDto = departmentServiceAPIClient.getByDepartmentId(employee.getDepartmentCode());
        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);
        OrganizationDto organizationDto= organizationServiceAPIClient.getByOrganizationCode(employee.getOrganizationCode());

        APIResponseDto apiResponseDto=new APIResponseDto(employeeDto,departmentDto,organizationDto);
        return apiResponseDto;
    }
    public APIResponseDto getDefaultDepartment(Long id,Exception e) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User","id",id)
        );
        DepartmentDto departmentDto=new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD0001");
        departmentDto.setDepartmentDescription("Reasearch and Development Department");
        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);
        OrganizationDto organizationDto=new OrganizationDto();
        organizationDto.setOrganizationName("Default Organization Name");
        organizationDto.setOrganizationDescription("Default Description");
        organizationDto.setOrganizationCode("Default Code");
        APIResponseDto apiResponseDto=new APIResponseDto(employeeDto,departmentDto,organizationDto);
        return apiResponseDto;
    }
}
