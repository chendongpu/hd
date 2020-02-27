package com.example.doctor.controller.response;

import com.example.doctor.model.User;
import com.example.doctor.model.UserArticle;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class ArticleResponse {

    private UserArticle userArticle;

    private User articleUser;

}
