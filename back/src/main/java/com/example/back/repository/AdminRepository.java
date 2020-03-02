package com.example.back.repository;

import com.example.back.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdminRepository extends JpaRepository<Admin,Long>, JpaSpecificationExecutor<Admin> {

}
