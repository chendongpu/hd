package com.example.demo.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class MsgRequest {

    @NotEmpty
    private String from;
    @NotEmpty
    private String to;
    @NotNull
    private Integer ope;
    @NotNull
    private Integer type;

    @NotNull
    private Body body;
}

@Getter
@Setter
@ToString
class Body{
    private String msg;
}
