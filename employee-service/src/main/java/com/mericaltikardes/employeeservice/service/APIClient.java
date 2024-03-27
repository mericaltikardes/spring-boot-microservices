package com.mericaltikardes.employeeservice.service;

import com.mericaltikardes.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8080",value = "DEPARTMENT-SERVICE")
public interface APIClient {
    @GetMapping("api/departments/{code}")
    DepartmentDto getByDepartmentId(@PathVariable("code") String departmentCode );

}