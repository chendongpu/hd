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
@Table(name="hd_user_article_collect")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserArticleCollectId.class)
public class UserArticleCollect implements Serializable {

    @Id
    private Long userid;

    @Id
    private Long aid;

}

class UserArticleCollectId implements Serializable {


    private Long userid;
    private Long aid;

}
