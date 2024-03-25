package com.mericaltikardes.departmentservice.service.Impl;

import com.mericaltikardes.departmentservice.dto.DepartmentDto;
import com.mericaltikardes.departmentservice.entity.Department;
import com.mericaltikardes.departmentservice.exception.ResourceNotFoundException;
import com.mericaltikardes.departmentservice.mapper.DepartmentMapper;
import com.mericaltikardes.departmentservice.repository.DepartmentRepository;
import com.mericaltikardes.departmentservice.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepository departmentRepository;



    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Optional<Department> department = departmentRepository.findByDepartmentCode(departmentCode);
        if(department.isEmpty()){
            throw new ResourceNotFoundException("Department Code: " +departmentCode +" is Not Found");
        }else {
        return DepartmentMapper.mapToDepartmentDto(department.get());
    }}
}
