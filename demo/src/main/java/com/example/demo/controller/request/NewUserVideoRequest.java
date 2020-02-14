package com.example.demo.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class NewUserVideoRequest {

    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String img;

    @NotEmpty
    private String video;


}