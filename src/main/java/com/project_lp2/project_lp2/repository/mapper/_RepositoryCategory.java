package com.project_lp2.project_lp2.repository.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.*;

import com.project_lp2.project_lp2.models.ModelCategory;

public interface _RepositoryCategory extends JpaRepository<ModelCategory, Long> {

    @Query("SELECT model FROM ModelCategory model WHERE model.categoryName LIKE %?1%")
    List<ModelCategory> findByName(String name);
}
