package com.example.doctor.repository;



import com.example.doctor.model.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserTestRepository extends JpaRepository<UserTest,Long>, JpaSpecificationExecutor<UserTest> {

}
