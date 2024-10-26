package com.project_lp2.project_lp2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import com.project_lp2.project_lp2.models.ModelCategory;
import com.project_lp2.project_lp2.repository.mapper._RepositoryCategory;

@Service
public class ServiceCategory {

    @Autowired
    private _RepositoryCategory repository;

    public ServiceCategory(_RepositoryCategory repository) {
        this.repository = repository;
    }

    public void serv_create_update_category(ModelCategory category) {
        repository.save(category);
    }

    public void serv_delete_category(long id) {
        repository.deleteById(id);
    }

    public List<ModelCategory> serv_list_category() {
        return repository.findAll();
    }

    public ModelCategory serv_find_category(long id) {
        return repository.findById(id).get();
    }

    public List<ModelCategory> serv_find_category_by_name(String name) {
        return repository.findByName(name);
    }
}
