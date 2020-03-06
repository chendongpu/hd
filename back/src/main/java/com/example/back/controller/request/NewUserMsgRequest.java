package com.example.back.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class NewUserMsgRequest {

    private Long id;

    @NotEmpty
    private String msg;


    @NotNull
    private Integer type;



    @NotNull
    private Long otherid;


}