package com.example.back.controller.request;

import com.example.back.model.UserTestQuestion;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@Setter
@ToString
public class NewUserTestRequest {

    private Long id;

    private Long userid;

    @NotEmpty
    private String title;


    @NotEmpty
    private String content;


    private Set<UserTestQuestion> userTestQuestions;





}