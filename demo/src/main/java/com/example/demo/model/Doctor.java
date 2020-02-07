package com.example.demo.model;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="hd_doctor")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String department;
    private String hospital;
    private String level;
    private String nickname;
    private String realname;
    private String mobile;
    private int isblack;
    private String username;
    private int mobileverify;
    private String password;
    private String content;
    @Column(updatable = false)
    @CreationTimestamp
    private Date createtime;
    private String birthyear;
    private String birthmonth;
    private String birthday;
    private int gender=0;
    private String avatar;
    private String province;
    private String city;
    private String area;

}
