package com.example.demo.model;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.persistence.*;
import java.util.Date;


@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="hd_treatment_order")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderid;
    private Long userid;
    private Long doctorid;

    @Type(type="org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
            parameters = {@org.hibernate.annotations.Parameter(name="currencyCode",value="CNY")})
    private Money money;
    private Integer duration;


    @Column(updatable = false)
    @CreationTimestamp
    private Date createtime;
    private Integer state;
}
