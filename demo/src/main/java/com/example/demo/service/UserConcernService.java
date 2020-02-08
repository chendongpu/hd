package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.UserConcern;
import com.example.demo.repository.UserConcernRepository;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Slf4j
@Service
public class UserConcernService {

    @Autowired
    private UserConcernRepository userConcernRepository;

    @Autowired
    private UserRepository userRepository;


    public UserConcern createUserConcern(UserConcern userConcern) {
        log.info("UserConcern:{}",userConcern);
        UserConcern saved = userConcernRepository.save(userConcern);
        log.info("New UserConcern:{}", saved);
        return saved;
    }

    public Optional<UserConcern> findOneUserConcern(Long userid,Long concernid){
        UserConcern userConcern = UserConcern.builder().userid(userid).concernid(concernid).build();
        Example<UserConcern> example = Example.of(userConcern);
        Optional<UserConcern> userConcernOptional= userConcernRepository.findOne(example);
        return userConcernOptional;
    }

    public void removeUserConcern(UserConcern userConcern){
        userConcernRepository.delete(userConcern);
    }

    public Page<User>  allUserConcern(UserConcern userConcern,Pageable pageable){
        Example<UserConcern> example = Example.of(userConcern);
        List<UserConcern> userConcernList= userConcernRepository.findAll(example);
        Long[] ids = new Long[userConcernList.size()];
        int i=0;
        for(UserConcern uc:userConcernList){
           ids[i]=uc.getConcernid();
           i++;
        }
        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                CriteriaBuilder.In<Long> in = cb.in(root.get("id"));
                for (Long id : ids) {
                    in.value(id);
                }
                list.add(in);
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };

        return userRepository.findAll(specification,pageable);
    }

    public Page<User>  allFans(UserConcern userConcern,Pageable pageable){
        Example<UserConcern> example = Example.of(userConcern);
        List<UserConcern> userConcernList= userConcernRepository.findAll(example);
        Long[] ids = new Long[userConcernList.size()];
        int i=0;
        for(UserConcern uc:userConcernList){
            ids[i]=uc.getUserid();
            i++;
        }
        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                CriteriaBuilder.In<Long> in = cb.in(root.get("id"));
                for (Long id : ids) {
                    in.value(id);
                }
                list.add(in);
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };

        return userRepository.findAll(specification,pageable);
    }




}
