package com.example.back.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@ToString
public class DoctorRequest {

    @NotNull
    private Long id;
    private String realname;
    private Integer isblack;
    private Long department;
    private String hospital;
    private String level;

    @Type(type="org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
            parameters = {@org.hibernate.annotations.Parameter(name="currencyCode",value="CNY")})
    private Money money;

    private String departmenttel;

}
