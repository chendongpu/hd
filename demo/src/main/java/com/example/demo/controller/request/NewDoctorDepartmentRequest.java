package com.example.demo.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class NewDoctorDepartmentRequest {

    private Long id;


    private String title;


    private String img;

}