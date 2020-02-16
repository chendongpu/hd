package com.example.demo.controller.packclass;

import com.example.demo.model.UserQuestion;
import com.example.demo.model.UserQuestionAnswer;
import com.example.demo.model.UserQuestionChoice;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PackUserQuestion {

    private UserQuestion userQuestion;

    private List<UserQuestionChoice> userQuestionChoiceList;

    private List<UserQuestionAnswer> userQuestionAnswerList;

    private boolean result;


}
