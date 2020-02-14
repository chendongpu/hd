package com.example.demo.controller.response;

import com.example.demo.model.User;
import com.example.demo.model.UserQuestionChoice;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class UserQuestionResponse {

    private Long id;

    private Long userid;


    private String title;


    private Integer type;

    private Integer score;
    private Date createtime;

    private User user;


    private List<UserQuestionChoice> userQuestionChoices;



}