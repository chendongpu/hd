package com.example.back.controller.response;

import com.example.back.model.DoctorDepartment;
import com.example.back.model.User;
import com.example.back.model.UserAddress;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class UserResponse {

    private User user;

    private UserAddress userAddress;

}
