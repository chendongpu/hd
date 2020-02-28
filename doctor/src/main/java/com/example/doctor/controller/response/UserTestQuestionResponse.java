package com.example.doctor.controller.response;


import com.example.doctor.model.UserTest;
import com.example.doctor.model.UserTest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class UserTestQuestionResponse {



    private UserTest usertest;


    private List<UserQuestionResponse> userQuestionResponses;



}