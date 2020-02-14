package com.example.demo.repository;



import com.example.demo.model.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserTestRepository extends JpaRepository<UserTest,Long>, JpaSpecificationExecutor<UserTest> {

}
