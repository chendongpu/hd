package com.example.back.repository;


import com.example.back.model.UserQuestionChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserQuestionChoiceRepository extends JpaRepository<UserQuestionChoice,Long>, JpaSpecificationExecutor<UserQuestionChoice> {

}
