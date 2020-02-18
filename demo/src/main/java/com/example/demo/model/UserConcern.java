package com.example.demo.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="hd_user_concern")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserConcernId.class)
public class UserConcern implements Serializable {

    @Id
    private Long userid;

    @Id
    private Long concernid;

}

class UserConcernId  implements Serializable {


    private Long userid;
    private Long concernid;

}
