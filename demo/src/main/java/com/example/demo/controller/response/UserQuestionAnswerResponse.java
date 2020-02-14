package com.example.demo.controller.response;

import com.example.demo.model.User;
import com.example.demo.model.UserQuestion;
import com.example.demo.model.UserQuestionAnswer;
import com.example.demo.model.UserQuestionChoice;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class UserQuestionAnswerResponse {

    private UserQuestion userQuestion;

    private List<UserQuestionChoice> userQuestionChoices;

    private List<UserQuestionChoice> userQuestionAnswer;



}