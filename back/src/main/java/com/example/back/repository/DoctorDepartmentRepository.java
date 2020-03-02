package com.example.back.repository;

import com.example.back.model.DoctorDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DoctorDepartmentRepository extends JpaRepository<DoctorDepartment,Long>, JpaSpecificationExecutor<DoctorDepartment> {

}
