package com.example.demo.model;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="hd_user_article")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(insertable = false,updatable = false)
    private Long userid;
    private String title;
    private String img;
    private String content;
    @Column(updatable = false)
    @CreationTimestamp
    private Date createtime;
    private String author;
    private Long readnum;
    private Long likenum;
    private String keyword;
    private Integer state;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="userid",referencedColumnName = "id")
    private User user;
}