package com.example.back.repository;



import com.example.back.model.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserTestRepository extends JpaRepository<UserTest,Long>, JpaSpecificationExecutor<UserTest> {

}
