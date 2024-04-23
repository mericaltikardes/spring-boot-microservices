package com.mericaltikardes.organizationservice.service;

import com.mericaltikardes.organizationservice.dto.OrganizationDto;

public interface OrganizationService {

    OrganizationDto saveOrganization(OrganizationDto organizationDto);

    OrganizationDto getOrganizationByOrganizationCode(String organizationCode);
}
