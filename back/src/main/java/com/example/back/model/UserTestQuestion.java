package com.example.back.model;


import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

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

class UserTestQuestionId implements Serializable {


    private Long testid;


    private Long questionid;

}

