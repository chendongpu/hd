package com.example.back.repository;

import com.example.back.model.UserTestReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserTestReportRepository extends JpaRepository<UserTestReport,Long>, JpaSpecificationExecutor<UserTestReport> {

}
