package com.example.back.controller.response;


import com.example.back.model.UserTest;
import com.example.back.model.UserTestReport;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class UserTestReportResponse {



    private UserTest usertest;


    private List<UserTestReport> userTestReports;



}