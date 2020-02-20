package com.example.demo.controller.response;

import com.example.demo.model.DoctorDepartment;
import com.example.demo.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class DoctorResponse {

    private User user;

    private DoctorDepartment doctorDepartment;

}
