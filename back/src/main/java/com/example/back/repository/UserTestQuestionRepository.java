package com.example.back.repository;



import com.example.back.model.UserTestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserTestQuestionRepository extends JpaRepository<UserTestQuestion,Long>, JpaSpecificationExecutor<UserTestQuestion> {

}
