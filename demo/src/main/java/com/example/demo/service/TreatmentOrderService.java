package com.example.demo.service;


import com.example.demo.model.TreatmentOrder;
import com.example.demo.repository.TreatmentOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Slf4j
@Service
public class TreatmentOrderService {

    @Autowired
    private TreatmentOrderRepository treatmentOrderRepository;

    public List<TreatmentOrder> findByUserId(Long userid){
        ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("userid",exact().ignoreCase());
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



}
