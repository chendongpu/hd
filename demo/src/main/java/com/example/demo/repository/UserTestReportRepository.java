package com.example.demo.repository;

import com.example.demo.model.UserTestReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserTestReportRepository extends JpaRepository<UserTestReport,Long>, JpaSpecificationExecutor<UserTestReport> {

}
