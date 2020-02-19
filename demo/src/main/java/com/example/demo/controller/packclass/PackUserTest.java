package com.example.demo.controller.packclass;

import com.example.demo.model.UserQuestion;
import com.example.demo.model.UserQuestionAnswer;
import com.example.demo.model.UserQuestionChoice;
import com.example.demo.model.UserTestReport;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PackUserTest {

   List<PackUserQuestion> packUserQuestionList;

   List<UserTestReport> userTestReportList;

   UserTestReport resultReport;

}
