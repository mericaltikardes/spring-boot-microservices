package com.mericaltikardes.organizationservice.controller;

import com.mericaltikardes.organizationservice.dto.OrganizationDto;
import com.mericaltikardes.organizationservice.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/organizations")
public class OrganizationController {

    private OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto){
        OrganizationDto savedOrganization = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
    }
    @GetMapping("{organizationCode}")
    public OrganizationDto getOrganization(@PathVariable String organizationCode){
        OrganizationDto organizationByOrganizationCode = organizationService.getOrganizationByOrganizationCode(organizationCode);
        return organizationByOrganizationCode;
    }
}
