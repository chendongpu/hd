package com.example.back.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@ToString
public class UserRequest {

    @NotNull
    private Long id;
    private String realname;
    private String password;
    private Integer isblack;

}
