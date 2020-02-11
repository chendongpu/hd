package com.example.demo.repository;


import com.example.demo.model.UserVideo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVideoRepository extends JpaRepository<UserVideo,Long> {

}
