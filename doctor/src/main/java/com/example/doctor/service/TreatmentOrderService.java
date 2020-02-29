package com.example.doctor.service;


import com.example.doctor.model.TreatmentOrder;
import com.example.doctor.repository.TreatmentOrderRepository;
import com.example.doctor.model.TreatmentOrder;
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
public class TreatmentOrderService {

    @Autowired
    private TreatmentOrderRepository treatmentOrderRepository;

    public List<TreatmentOrder> findByUserId(Long userid){
        ExampleMatcher matcher= ExampleMatcher.matching().withMatcher("userid",exact().ignoreCase());
        List<TreatmentOrder> treatmentOrderList =treatmentOrderRepository.findAll(Example.of(TreatmentOrder.builder().userid(userid).build(),matcher));
        log.info("treatmentOrderList Found:{}",treatmentOrderList);
        return treatmentOrderList;
    }


    public Optional<TreatmentOrder> findTreatmentOrderById(Long id){
        Optional<TreatmentOrder> treatmentOrder =treatmentOrderRepository.findById(id);
        log.info("treatmentOrder Found:{}",treatmentOrder);
        return treatmentOrder;
    }

    public TreatmentOrder createTreatmentOrder(TreatmentOrder treatmentOrder) {
        log.info("treatmentOrder:{}",treatmentOrder);
        TreatmentOrder saved = treatmentOrderRepository.save(treatmentOrder);
        log.info("New TreatmentOrder:{}", saved);
        return saved;
    }

    public void updateTreatmentOrder(TreatmentOrder newTreatmentOrder){
        treatmentOrderRepository.save(newTreatmentOrder);
    }

    public void removeTreatmentOrder(Long id){
        treatmentOrderRepository.deleteById(id);
    }


    public Page<TreatmentOrder> allTreatmentOrder(TreatmentOrder treatmentOrder, Pageable pageable){
        Example<TreatmentOrder> example = Example.of(treatmentOrder);
        return treatmentOrderRepository.findAll(example,pageable);
    }

    public Page<TreatmentOrder> findTreatmentOrder(TreatmentOrder treatmentOrder, Pageable pageable,Integer type) {

        Specification<TreatmentOrder> specification = new Specification<TreatmentOrder>() {
            @Override
            public Predicate toPredicate(Root<TreatmentOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                list.add(cb.equal(root.get("userid"), treatmentOrder.getUserid()));
                //type 1 待接诊 2 进行中 3 已完结
                if (type == 1) {
                    list.add(cb.equal(root.get("state"), 1));
                    list.add(cb.equal(root.get("begintime"), 0));
                    list.add(cb.equal(root.get("endtime"), 0));
                }

                if (type == 2) {
                    list.add(cb.equal(root.get("state"), 1));
                    list.add(cb.gt(root.get("begintime"), 0));
                }

                if (type == 3) {
                    list.add(cb.gt(root.get("begintime"), 0));
                    list.add(cb.gt(root.get("endtime"), 0));
                }

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };

        return treatmentOrderRepository.findAll(specification, pageable);
    }





}
