package com.example.demo.model;


import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="hd_user_article_comment_like")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserArticleCommentLikeId.class)
public class UserArticleCommentLike  implements Serializable {

    @Id
    private Long userid;

    @Id
    private Long articleid;

    @Id
    private Long commentid;

}

class UserArticleCommentLikeId  implements Serializable {
    private Long userid;
    private Long articleid;
    private Long commentid;
}
