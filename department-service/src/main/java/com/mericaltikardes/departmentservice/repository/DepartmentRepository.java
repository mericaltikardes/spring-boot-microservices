package com.mericaltikardes.departmentservice.repository;

import com.mericaltikardes.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

    Optional<Department> findByDepartmentCode(String departmentCode);
}
