package com.example.doctor.repository;



import com.example.doctor.model.UserQuestionChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserQuestionChoiceRepository extends JpaRepository<UserQuestionChoice,Long>, JpaSpecificationExecutor<UserQuestionChoice> {

}
