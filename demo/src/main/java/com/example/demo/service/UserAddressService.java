package com.example.demo.service;

import com.example.demo.model.UserAddress;
import com.example.demo.repository.UserAddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Slf4j
@Service
public class UserAddressService {

    @Autowired
    private UserAddressRepository userAddressRepository;

    public Optional<UserAddress> findByMobile(String mobile){
        ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("mobile",exact().ignoreCase());
        Optional<UserAddress> userAddress =userAddressRepository.findOne(Example.of(UserAddress.builder().mobile(mobile).build(),matcher));
        log.info("UserAddress Found:{}",userAddress);
       return userAddress;
    }


    public Optional<UserAddress> findUserAddressById(Long id){
        Optional<UserAddress> userAddress =userAddressRepository.findById(id);
        log.info("UserAddress Found:{}",userAddress);
        return userAddress;
    }

    public UserAddress createUserAddress(UserAddress newUserAddress) {
        log.info("UserAddress:{}",newUserAddress);
        UserAddress saved = userAddressRepository.save(newUserAddress);
        log.info("New UserAddress:{}", saved);
        return saved;
    }

    public void removeUserAddress(Long id){
        userAddressRepository.deleteById(id);
    }

    public void updateUserAddress(UserAddress newUserAddress){
        userAddressRepository.save(newUserAddress);
    }

    public Page<UserAddress> allUserAddress(UserAddress userAddress,Pageable pageable){
        Example<UserAddress> example = Example.of(userAddress);
        return userAddressRepository.findAll(example,pageable);
    }
}
