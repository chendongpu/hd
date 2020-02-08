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
@Table(name="hd_user")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String realname;
    private String mobile;
    private Integer isblack;
    private String username;
    private Integer mobileverify;
    private String password;
    private String content;
    @Column(updatable = false)
    @CreationTimestamp
    private Date createtime;
    private String birthyear;
    private String birthmonth;
    private String birthday;
    private Integer gender;
    private String avatar;
    private String province;
    private String city;
    private String area;









}
