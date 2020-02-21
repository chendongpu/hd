package com.example.demo.repository;

import com.example.demo.model.TreatmentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TreatmentOrderRepository extends JpaRepository<TreatmentOrder,Long>, JpaSpecificationExecutor<TreatmentOrder> {

}
