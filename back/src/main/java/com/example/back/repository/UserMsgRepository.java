package com.example.back.repository;


import com.example.back.model.UserMsg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMsgRepository extends JpaRepository<UserMsg,Long> {

}
