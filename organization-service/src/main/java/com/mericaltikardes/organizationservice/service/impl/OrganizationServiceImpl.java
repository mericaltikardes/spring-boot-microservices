package com.mericaltikardes.organizationservice.service.impl;

import com.mericaltikardes.organizationservice.dto.OrganizationDto;
import com.mericaltikardes.organizationservice.entity.Organization;
import com.mericaltikardes.organizationservice.mapper.OrganizationMapper;
import com.mericaltikardes.organizationservice.repository.OrganizationsRepository;
import com.mericaltikardes.organizationservice.service.OrganizationService;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationsRepository organizationsRepository;

    public OrganizationServiceImpl(OrganizationsRepository organizationsRepository) {
        this.organizationsRepository = organizationsRepository;
    }

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);
        organizationsRepository.save(organization);
        OrganizationDto savedOrganization = OrganizationMapper.mapToOrganizationDto(organization);
        return savedOrganization;
    }

    @Override
    public OrganizationDto getOrganizationByOrganizationCode(String organizationCode) {
        Organization organization = organizationsRepository.getOrganizationByOrOrganizationCode(organizationCode);
        return OrganizationMapper.mapToOrganizationDto(organization);
    }
}
