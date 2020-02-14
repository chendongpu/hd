package com.example.demo.repository;



import com.example.demo.model.UserTestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserTestQuestionRepository extends JpaRepository<UserTestQuestion,Long>, JpaSpecificationExecutor<UserTestQuestion> {

}
