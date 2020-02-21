package com.example.demo.service;

import com.example.demo.model.DoctorDepartment;
import com.example.demo.model.User;
import com.example.demo.model.UserArticle;
import com.example.demo.repository.UserArticleRepository;
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
public class UserArticleService {

    @Autowired
    private UserArticleRepository userArticleRepository;

    public Optional<UserArticle> findByTitle(String title){
        ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("title",exact().ignoreCase());
        Optional<UserArticle> userArticle =userArticleRepository.findOne(Example.of(UserArticle.builder().title(title).build(),matcher));
        log.info("userArticle Found:{}",userArticle);
        return userArticle;
    }

    public Optional<UserArticle> findUserArticleById(Long id){
        Optional<UserArticle> userArticle =userArticleRepository.findById(id);
        log.info("userArticle Found:{}",userArticle);
        return userArticle;
    }

    public UserArticle createUserArticle(UserArticle newUserArticle) {
        log.info("UserArticle:{}",newUserArticle);
        UserArticle saved = userArticleRepository.save(newUserArticle);
        log.info("New UserArticle:{}", saved);
        return saved;
    }

    public void removeUserArticle(Long id){
        userArticleRepository.deleteById(id);
    }

    public void updateUserArticle(UserArticle newUserArticle){
        userArticleRepository.save(newUserArticle);
    }

    public Page<UserArticle> allUserArticle(UserArticle UserArticle,Pageable pageable){
        Example<UserArticle> example = Example.of(UserArticle);
        return userArticleRepository.findAll(example,pageable);
    }

    public Page<UserArticle> findByKeyword(String keyword, Pageable pageable){

        Specification<UserArticle> specification = new Specification<UserArticle>() {
            @Override
            public Predicate toPredicate(Root<UserArticle> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                list.add(cb.equal(root.get("state"), 1));

                Predicate[] p = new Predicate[list.size()];



                Predicate pre=cb.and(list.toArray(p));

                List<Predicate> list1 = new ArrayList<Predicate>();
                log.info("keyword:{}",keyword);
                list1.add(cb.like(root.get("title").as(String.class),  "%" + keyword + "%"));
                list1.add(cb.like(root.get("content").as(String.class), "%" + keyword + "%"));
                list1.add(cb.like(root.get("keyword").as(String.class), "%" + keyword + "%"));

                Predicate[] p1 = new Predicate[list1.size()];
                Predicate pre1=cb.or(list1.toArray(p1));



                Predicate pdt=query.where(pre,pre1).getRestriction();
                return pdt;
            }
        };


        return userArticleRepository.findAll(specification,pageable);
    }
}
