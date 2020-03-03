package com.example.demo.service;

import com.example.demo.model.DoctorDepartment;
import com.example.demo.model.User;
import com.example.demo.model.UserArticle;
import com.example.demo.model.UserArticleRead;
import com.example.demo.repository.DoctorDepartmentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.MD5Utils;
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
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByUsername(String username){
        ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("username",exact().ignoreCase());
        Optional<User> user =userRepository.findOne(Example.of(User.builder().username(username).isdelete(0).build(),matcher));
        log.info("User Found:{}",user);
        return user;
    }

    public Optional<User> findByMobile(String mobile){
        ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("mobile",exact().ignoreCase());
        Optional<User> user =userRepository.findOne(Example.of(User.builder().mobile(mobile).isdelete(0).build(),matcher));
        log.info("User Found:{}",user);
        return user;
    }


    public Optional<User> findUserById(Long id){
        Optional<User> user =userRepository.findById(id);
        log.info("User Found:{}",user);
        return user;
    }

    public User createUser(String mobile, String password) {
        User user = User.builder().mobile(mobile).password(MD5Utils.stringToMD5(password)).content("").build();
        log.info("User:{}",user);
        User saved = userRepository.save(user);
        saved.setUsername("hd"+(saved.getId()+10000));
        this.updateUser(saved);
        log.info("New User:{}", saved);
        return saved;
    }

    public void updateUser(User newUser){
        userRepository.save(newUser);
    }

    public Page<User> allDoctor(User user, Pageable pageable) {
        Example<User> example = Example.of(user);
        return userRepository.findAll(example,pageable);
    }

    @Autowired
    private DoctorDepartmentRepository doctorDepartmentRepository;

    public Page<User> findByKeyword(String keyword, Pageable pageable){

        DoctorDepartment doctorDepartment =new DoctorDepartment();
        doctorDepartment.setTitle(keyword);
        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);  //改变默认字符串匹配为:模糊查询

        Example<DoctorDepartment> example = Example.of(doctorDepartment,matcher);
        List<DoctorDepartment> doctorDepartmentList= doctorDepartmentRepository.findAll(example);
        Long[] ids = new Long[doctorDepartmentList.size()];
        int i=0;
        for(DoctorDepartment dd:doctorDepartmentList){
            ids[i]=dd.getId();
            i++;
        }
        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                list.add(cb.equal(root.get("isdoctor"), 1));
                list.add(cb.equal(root.get("isdelete"), 0));

                Predicate[] p = new Predicate[list.size()];



                Predicate pre=cb.and(list.toArray(p));

                List<Predicate> list1 = new ArrayList<Predicate>();
                log.info("keyword:{}",keyword);
                list1.add(cb.like(root.get("realname").as(String.class),  "%" + keyword + "%"));
                list1.add(cb.like(root.get("username").as(String.class), "%" + keyword + "%"));
                list1.add(cb.like(root.get("hospital").as(String.class), "%" + keyword + "%"));
                list1.add(cb.like(root.get("level").as(String.class), "%" + keyword + "%"));
                list1.add(cb.like(root.get("goodat").as(String.class), "%" + keyword + "%"));

                CriteriaBuilder.In<Long> in = cb.in(root.get("department"));
                for (Long id : ids) {
                    in.value(id);
                }
                list1.add(in);

                Predicate[] p1 = new Predicate[list1.size()];
                Predicate pre1=cb.or(list1.toArray(p1));



                Predicate pdt=query.where(pre,pre1).getRestriction();
                return pdt;
            }
        };

        return userRepository.findAll(specification,pageable);
    }

}
