package com.example.demo.repository;


import com.example.demo.model.UserTaskLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserTaskLogRepository extends JpaRepository<UserTaskLog,Long>, JpaSpecificationExecutor<UserTaskLog> {

}
