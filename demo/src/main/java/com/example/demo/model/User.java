package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="hd_user")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String realname;
    private String mobile;
    private Integer isblack;
    private String username;
    private Integer mobileverify;

    //隐藏输出但是不隐藏输入
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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


    private Integer isdoctor;
    private Long department;
    private String hospital;
    private String level;
    private String goodat;
    @Type(type="org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
            parameters = {@org.hibernate.annotations.Parameter(name="currencyCode",value="CNY")})
    private Money money;
    private Integer duration;

    private String departmenttel;



}
