package com.example.back.controller.packclass;

import com.example.back.model.UserArticleComment;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PackUserArticleComment {

    private UserArticleComment userArticleComment;

    private List<UserArticleComment> userArticleReplyList;


}
