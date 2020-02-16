package com.example.demo.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public
@Getter
@Setter
@ToString
class QuestionChoiceInput{

    private Long questionid;

    private Long[] choiceid;
}
