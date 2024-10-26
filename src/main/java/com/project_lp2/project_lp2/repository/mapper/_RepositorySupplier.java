package com.project_lp2.project_lp2.repository.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project_lp2.project_lp2.models.ModelSupplier;
import java.util.*;

public interface _RepositorySupplier extends JpaRepository<ModelSupplier, Long> {

    @Query("SELECT model FROM ModelSupplier model WHERE model.supplierName LIKE %?1%")
    List<ModelSupplier> findByName(String name);
}
