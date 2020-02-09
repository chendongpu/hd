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
@Table(name="hd_user_article_log")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserArticleLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userid;
    private Long aid;
    private Integer read;
    private Integer like;
    @Column(updatable = false)
    @CreationTimestamp
    private Date createtime;

}
