package com.example.back.repository;


import com.example.back.model.UserTestLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserTestLogRepository extends JpaRepository<UserTestLog,Long>, JpaSpecificationExecutor<UserTestLog> {

}
