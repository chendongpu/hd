package com.example.back.repository;


import com.example.back.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress,Long> {

}
