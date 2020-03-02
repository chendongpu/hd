package com.example.back.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="hd_admin")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Admin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    //隐藏输出但是不隐藏输入
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(updatable = false)
    @CreationTimestamp
    private Date createtime;




}
