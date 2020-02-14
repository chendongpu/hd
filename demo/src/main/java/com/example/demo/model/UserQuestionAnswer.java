package com.example.demo.model;


import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="hd_user_question_answer")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserQuestionAnswerId.class)
public class UserQuestionAnswer {

    @Id
    private Long questionid;

    @Id
    private Long choiceid;

}
