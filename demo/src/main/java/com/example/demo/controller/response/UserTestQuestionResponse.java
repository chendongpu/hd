package com.example.demo.controller.response;


import com.example.demo.model.UserTest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class UserTestQuestionResponse {



    private UserTest usertest;


    private List<UserQuestionResponse> userQuestionResponses;



}