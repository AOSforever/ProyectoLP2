package com.project_lp2.project_lp2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.project_lp2.project_lp2.models.ModelSupplier;
import com.project_lp2.project_lp2.repository.mapper._RepositorySupplier;

@Service
public class ServiceSupplier {

    @Autowired
    private _RepositorySupplier repository;

    public ServiceSupplier(_RepositorySupplier repository) {
        this.repository = repository;
    }

    public List<ModelSupplier> serv_list_supplier() {
        return repository.findAll();
    }

    public ModelSupplier serv_get_supplier(long id) {
        return repository.findById(id).get();
    }

    public void serv_create_update_supplier(ModelSupplier supplier) {
        repository.save( supplier );
    }

    public void serv_delete_supplier(long id) {
        repository.deleteById(id);
    }

    public List<ModelSupplier> serv_find_supplier(String name) {
        return repository.findByName(name);
    }
}
