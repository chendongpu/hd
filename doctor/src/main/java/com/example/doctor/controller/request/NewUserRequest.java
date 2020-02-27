package com.example.doctor.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@ToString
public class NewUserRequest {

    private String mobile;


    private String username;

    @NotEmpty
    private String password;

}