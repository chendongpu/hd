package com.example.demo.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
public class NewUserRequest {
    private String mobile;
    private String username;
    private String password;

}