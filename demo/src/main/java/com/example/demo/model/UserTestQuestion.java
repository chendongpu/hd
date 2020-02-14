package com.example.demo.model;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="hd_user_test_question")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserTestQuestion.class)
public class UserTestQuestion implements Serializable {

    @Id
    private Long testid;


    @Id
    private Long questionid;

}
