package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.UserArticle;
import com.example.demo.model.UserArticleCollect;
import com.example.demo.repository.UserArticleCollectRepository;
import com.example.demo.repository.UserArticleRepository;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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

@Slf4j
@Service
public class UserArticleCollectService {

    @Autowired
    private UserArticleCollectRepository userArticleCollectRepository;

    @Autowired
    private UserArticleRepository userArticleRepository;


    public UserArticleCollect createUserArticleCollect(UserArticleCollect userArticleCollect) {
        log.info("UserArticleCollect:{}",userArticleCollect);
        UserArticleCollect saved = userArticleCollectRepository.save(userArticleCollect);
        log.info("New UserArticleCollect:{}", saved);
        return saved;
    }

    public Optional<UserArticleCollect> findOneUserArticleCollect(Long userid,Long aid){
        UserArticleCollect userArticleCollect = UserArticleCollect.builder().userid(userid).aid(aid).build();
        Example<UserArticleCollect> example = Example.of(userArticleCollect);
        Optional<UserArticleCollect> userArticleCollectOptional= userArticleCollectRepository.findOne(example);
        return userArticleCollectOptional;
    }

    public void removeUserArticleCollect(UserArticleCollect userArticleCollect){
        userArticleCollectRepository.delete(userArticleCollect);
    }

    public Page<UserArticle>  allUserArticleCollect(UserArticleCollect userArticleCollect,Pageable pageable){
        Example<UserArticleCollect> example = Example.of(userArticleCollect);
        List<UserArticleCollect> userArticleCollectList= userArticleCollectRepository.findAll(example);
        Long[] ids = new Long[userArticleCollectList.size()];
        int i=0;
        for(UserArticleCollect uac:userArticleCollectList){
           ids[i]=uac.getAid();
           i++;
        }
        Specification<UserArticle> specification = new Specification<UserArticle>() {
            @Override
            public Predicate toPredicate(Root<UserArticle> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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

        return userArticleRepository.findAll(specification,pageable);
    }





}
