package com.example.doctor.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class NewUserArticleRequest {

    private Long id;

    @NotEmpty
    private String title;

    @NotNull
    private Integer type;

    @NotEmpty
    private String img;

    @NotEmpty
    private String content;

    @NotEmpty
    private String author;

    @NotEmpty
    private String keyword;


}