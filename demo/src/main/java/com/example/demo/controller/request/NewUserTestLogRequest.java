package com.example.demo.controller.request;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@ToString
public class NewUserTestLogRequest {

    @NotNull
    private Long testid;

    @NotEmpty
    private Set<QuestionChoiceInput> questionChoiceInputSet;

}
