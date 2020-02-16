package com.example.demo.repository;

import com.example.demo.model.PointTask;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PointTaskRepository extends JpaRepository<PointTask,Long>, JpaSpecificationExecutor<PointTask> {

}
