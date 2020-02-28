package com.example.doctor.repository;



import com.example.doctor.model.UserQuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserQuestionAnswerRepository extends JpaRepository<UserQuestionAnswer,Long>, JpaSpecificationExecutor<UserQuestionAnswer> {

}
