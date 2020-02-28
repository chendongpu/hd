package com.example.doctor.controller.request;

import com.example.doctor.model.UserTestReport;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
public class NewUserTestReportRequest {

    private Long testid;

    private List<UserTestReport> userTestReportList;
}
