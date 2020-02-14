package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="hd_user_test")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(insertable = false,updatable = false)
    private Long userid;
    private String title;
    private String content;
    @Column(updatable = false)
    @CreationTimestamp
    private Date createtime;
    private Integer state;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="userid",referencedColumnName = "id")
    private User user;

}
