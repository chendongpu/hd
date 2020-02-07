package com.example.demo.model;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="hd_article")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String article_title;
    private String article_img;
    private String article_content;
    @Column(updatable = false)
    @CreationTimestamp
    private Date createtime;
    private String article_author;
    private Long article_readnum;
    private Long article_likenum;
    private String article_keyword;
    private String article_state;
}
