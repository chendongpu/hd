package com.example.demo.model;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="hd_user_address")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int userid;
    private String realname;
    private String mobile;
    private String province;
    private String city;
    private String area;
    private String address;
    private int isdefault;
    private String zipcode;
    private int deleted;
    private String street;
    private String lng;
    private String lat;
    @Column(updatable = false)
    @CreationTimestamp
    private Date createtime;
}
