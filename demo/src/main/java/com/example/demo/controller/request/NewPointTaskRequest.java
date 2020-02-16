package com.example.demo.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class NewPointTaskRequest {

    private Long id;

    @NotEmpty
    private String title;

    @NotNull
    private Integer type;

    @NotNull
    private Integer point;




}