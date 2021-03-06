package com.example.doctor.service;

import com.example.doctor.model.User;
import com.example.doctor.model.UserQuestion;
import com.example.doctor.model.UserTestQuestion;
import com.example.doctor.repository.UserQuestionRepository;
import com.example.doctor.repository.UserRepository;
import com.example.doctor.repository.UserTestQuestionRepository;
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
public class UserTestQuestionService {


    @Autowired
    private UserTestQuestionRepository userTestQuestionRepository;

    @Autowired
    private UserQuestionRepository userQuestionRepository;

    @Autowired
    private UserRepository userRepository;


    public UserTestQuestion createUserTestQuestion(UserTestQuestion userTestQuestion) {
        log.info("UserTestQuestion:{}",userTestQuestion);
        UserTestQuestion saved = userTestQuestionRepository.save(userTestQuestion);
        log.info("New UserTestQuestion:{}", saved);
        return saved;
    }

    public List<UserTestQuestion> createBatchUserTestQuestion(List<UserTestQuestion> userTestQuestionList) {
        List<UserTestQuestion> saved = userTestQuestionRepository.saveAll(userTestQuestionList);
        log.info("New UserTestQuestion:{}", saved);
        return saved;
    }



    public Optional<UserTestQuestion> findOneUserTestQuestion(Long testid,Long questionid){
        UserTestQuestion userTestQuestion = UserTestQuestion.builder().testid(testid).questionid(questionid).build();
        Example<UserTestQuestion> example = Example.of(userTestQuestion);
        Optional<UserTestQuestion> userTestQuestionOptional= userTestQuestionRepository.findOne(example);
        return userTestQuestionOptional;
    }

    public List<UserQuestion> findOneUserTestQuestionList(Long testid){
        UserTestQuestion userTestQuestion = UserTestQuestion.builder().testid(testid).build();
        Example<UserTestQuestion> example = Example.of(userTestQuestion);
        List<UserTestQuestion> userTestQuestionList= userTestQuestionRepository.findAll(example);
        Long[] ids = new Long[userTestQuestionList.size()];
        int i=0;
        for(UserTestQuestion uc:userTestQuestionList){
            ids[i]=uc.getQuestionid();
            i++;
        }
        Specification<UserQuestion> specification = new Specification<UserQuestion>() {
            @Override
            public Predicate toPredicate(Root<UserQuestion> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
        List<UserQuestion> userQuestionList = userQuestionRepository.findAll(specification);
        return userQuestionList;
    }


    public void removeUserTestQuestion(UserTestQuestion userTestQuestion){
        userTestQuestionRepository.delete(userTestQuestion);
    }

    public Page<User> allUserTestQuestion(UserTestQuestion userTestQuestion, Pageable pageable){
        Example<UserTestQuestion> example = Example.of(userTestQuestion);
        List<UserTestQuestion> userTestQuestionList= userTestQuestionRepository.findAll(example);
        Long[] ids = new Long[userTestQuestionList.size()];
        int i=0;
        for(UserTestQuestion uc:userTestQuestionList){
            ids[i]=uc.getQuestionid();
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
