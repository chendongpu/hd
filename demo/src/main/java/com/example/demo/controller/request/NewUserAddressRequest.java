package com.example.demo.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class NewUserAddressRequest {

    private Long id;

    private Long userid;

    @NotEmpty
    private String realname;

    @NotEmpty
    private String mobile;

    @NotEmpty
    private String province;

    @NotEmpty
    private String city;

    @NotEmpty
    private String area;

    @NotEmpty
    private String address;


}