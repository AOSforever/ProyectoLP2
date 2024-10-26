package com.project_lp2.project_lp2.repository.mapper;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project_lp2.project_lp2.models.ModelCustomer;

public interface _RepositoryCustomer extends JpaRepository<ModelCustomer, Long> {

    @Query("SELECT c FROM ModelCustomer c WHERE c.customerName LIKE %?1%")
    List<ModelCustomer> findByName(String name);

}