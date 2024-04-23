package com.mericaltikardes.organizationservice.repository;

import com.mericaltikardes.organizationservice.dto.OrganizationDto;
import com.mericaltikardes.organizationservice.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationsRepository extends JpaRepository<Organization,Long>{
    Organization getOrganizationByOrOrganizationCode(String organizationCode);
}
