package com.example.demo.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.List;


@Getter
@Setter
@ToString
public class NewUserRequest {
    private String mobile;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

}