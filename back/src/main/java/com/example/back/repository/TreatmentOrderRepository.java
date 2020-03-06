package com.example.back.repository;

import com.example.back.model.TreatmentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TreatmentOrderRepository extends JpaRepository<TreatmentOrder,Long>, JpaSpecificationExecutor<TreatmentOrder> {

}
