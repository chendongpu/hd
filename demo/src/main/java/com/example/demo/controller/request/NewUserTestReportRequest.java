package com.example.demo.controller.request;

import com.example.demo.model.UserTestReport;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;


@Getter
@Setter
@ToString
public class NewUserTestReportRequest {

    private Long testid;

    private List<UserTestReport> userTestReportList;
}
