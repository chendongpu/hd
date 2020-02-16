package com.example.demo.repository;


import com.example.demo.model.UserMsg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMsgRepository extends JpaRepository<UserMsg,Long> {

}
