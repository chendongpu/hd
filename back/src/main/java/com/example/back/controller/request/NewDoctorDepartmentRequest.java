package com.example.back.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewDoctorDepartmentRequest {

    private Long id;


    private String title;


    private String img;

}