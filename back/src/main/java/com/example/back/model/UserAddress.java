package com.example.back.model;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@DynamicInsert
@DynamicUpdate
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
    private Long userid;
    private String realname;
    private String mobile;
    private String province;
    private String city;
    private String area;
    private String address;
    private Integer isdefault;
    private String zipcode;
    private Integer deleted;
    private String street;
    private String lng;
    private String lat;
    @Column(updatable = false)
    @CreationTimestamp
    private Date createtime;

}
