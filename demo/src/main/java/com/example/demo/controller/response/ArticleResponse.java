package com.example.demo.controller.response;

import com.example.demo.model.DoctorDepartment;
import com.example.demo.model.User;
import com.example.demo.model.UserArticle;
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
