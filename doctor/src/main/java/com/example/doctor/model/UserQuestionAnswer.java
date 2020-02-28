package com.example.doctor.model;


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

class UserQuestionAnswerId implements Serializable {


    private Long questionid;


    private Long choiceid;

}
