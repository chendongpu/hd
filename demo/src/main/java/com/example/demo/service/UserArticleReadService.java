package com.example.demo.service;

import com.example.demo.model.UserArticle;
import com.example.demo.model.UserArticleRead;
import com.example.demo.repository.UserArticleReadRepository;
import com.example.demo.repository.UserArticleRepository;
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
public class UserArticleReadService {

    @Autowired
    private UserArticleReadRepository userArticleReadRepository;

    @Autowired
    private UserArticleRepository userArticleRepository;


    public UserArticleRead createUserArticleRead(UserArticleRead userArticleRead) {
        log.info("UserArticleRead:{}",userArticleRead);
        UserArticleRead saved = userArticleReadRepository.save(userArticleRead);
        log.info("New UserArticleRead:{}", saved);
        return saved;
    }

    public Optional<UserArticleRead> findOneUserArticleRead(Long userid,Long aid){
        UserArticleRead userArticleRead = UserArticleRead.builder().userid(userid).aid(aid).build();
        Example<UserArticleRead> example = Example.of(userArticleRead);
        Optional<UserArticleRead> userArticleReadOptional= userArticleReadRepository.findOne(example);
        return userArticleReadOptional;
    }

    public void removeUserArticleRead(UserArticleRead userArticleRead){
        userArticleReadRepository.delete(userArticleRead);
    }

    public Page<UserArticle>  allUserArticleRead(UserArticleRead userArticleRead,Pageable pageable){
        Example<UserArticleRead> example = Example.of(userArticleRead);
        List<UserArticleRead> userArticleReadList= userArticleReadRepository.findAll(example);
        Long[] ids = new Long[userArticleReadList.size()];
        int i=0;
        for(UserArticleRead uac:userArticleReadList){
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
