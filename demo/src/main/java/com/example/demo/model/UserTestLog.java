package com.example.demo.model;


import com.example.demo.util.StringJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="hd_user_test_log")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserTestLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userid;
    private Long testid;
    private String title;
    private String content;
    @Column(updatable = false)
    @CreationTimestamp
    private Date createtime;

    private Integer score;//测评得分

    @JsonSerialize(using = StringJsonSerializer.class)
    private String result;


}
