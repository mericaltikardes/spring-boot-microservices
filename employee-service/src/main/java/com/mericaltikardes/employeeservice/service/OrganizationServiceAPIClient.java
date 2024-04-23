package com.mericaltikardes.employeeservice.service;

import com.mericaltikardes.employeeservice.dto.DepartmentDto;
import com.mericaltikardes.employeeservice.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORGANIZATION-SERVICE")
public interface OrganizationServiceAPIClient {
    @GetMapping("api/organizations/{organizationCode}")
    OrganizationDto getByOrganizationCode(@PathVariable String organizationCode );
}
