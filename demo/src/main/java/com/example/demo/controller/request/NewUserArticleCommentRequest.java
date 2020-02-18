package com.example.demo.controller.request;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@Setter
@ToString
public class NewUserArticleCommentRequest {



    private Long articleid;

    @NotEmpty
    private String comment;


}