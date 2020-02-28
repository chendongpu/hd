package com.example.doctor.controller.response;

import com.example.doctor.model.UserQuestion;
import com.example.doctor.model.UserQuestionChoice;
import com.example.doctor.model.UserQuestion;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class UserQuestionAnswerResponse {

    private UserQuestion userQuestion;

    private List<UserQuestionChoice> userQuestionChoices;

    private List<UserQuestionChoice> userQuestionAnswer;



}