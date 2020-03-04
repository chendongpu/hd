package com.example.demo.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class QuestionChoiceInput{

    private Long questionid;

    private Long[] choiceid;
}
