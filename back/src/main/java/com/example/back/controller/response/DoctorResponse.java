package com.example.back.controller.response;

import com.example.back.model.DoctorDepartment;
import com.example.back.model.User;
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
