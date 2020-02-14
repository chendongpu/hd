package com.example.demo.model;


import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="hd_user_question_choice")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserQuestionChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(insertable = false,updatable = false)
    private Long questionid;
    private String choice;

    @ManyToOne(targetEntity = UserQuestion.class)
    @JoinColumn(name="questionid",referencedColumnName = "id")
    private UserQuestion question;
}
