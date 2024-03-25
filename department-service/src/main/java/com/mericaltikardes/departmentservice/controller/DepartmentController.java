package com.mericaltikardes.departmentservice.controller;

import com.mericaltikardes.departmentservice.dto.DepartmentDto;
import com.mericaltikardes.departmentservice.service.DepartmentService;
import com.mericaltikardes.departmentservice.service.Impl.DepartmentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartment= departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping("{code}")
    public DepartmentDto getByDepartmentId(@PathVariable("code") String departmentCode ){
        return departmentService.getDepartmentByCode(departmentCode);
    }

}
