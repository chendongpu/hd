package com.example.doctor.controller.request;

import com.example.doctor.model.UserQuestionChoice;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
public class NewUserQuestionRequest {

    private Long id;

    private Long userid;

    @NotEmpty
    private String title;

    @NotNull
    private Integer type;

    @NotNull
    private Integer score;


    private List<UserQuestionChoice> userQuestionChoices;





}