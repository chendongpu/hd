package com.example.doctor.repository;


import com.example.doctor.model.UserCashLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserCashLogRepository extends JpaRepository<UserCashLog,Long>, JpaSpecificationExecutor<UserCashLog> {

}
