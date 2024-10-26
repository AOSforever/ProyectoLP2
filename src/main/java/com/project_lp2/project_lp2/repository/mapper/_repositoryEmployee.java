package com.project_lp2.project_lp2.repository.mapper;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project_lp2.project_lp2.models.ModelEmployee;

public interface _repositoryEmployee extends JpaRepository<ModelEmployee, Long> {

    @Query("SELECT model FROM ModelEmployee model WHERE model.employeeName LIKE %?1%")
    List<ModelEmployee> findByName(String name);
}
