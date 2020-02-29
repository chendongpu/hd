package com.example.doctor.repository;

import com.example.doctor.model.TreatmentOrder;
import com.example.doctor.model.TreatmentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TreatmentOrderRepository extends JpaRepository<TreatmentOrder,Long>, JpaSpecificationExecutor<TreatmentOrder> {

}
