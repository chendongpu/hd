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
public class UserVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long doctorid;
    private String title;
    private String video_img;
    private String video_url;
    @Column(updatable = false)
    @CreationTimestamp
    private Date createtime;


}
