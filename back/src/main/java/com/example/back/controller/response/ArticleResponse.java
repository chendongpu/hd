package com.example.back.controller.response;

import com.example.back.model.User;
import com.example.back.model.UserArticle;
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
