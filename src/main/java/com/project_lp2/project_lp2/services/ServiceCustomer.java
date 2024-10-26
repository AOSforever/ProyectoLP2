package com.project_lp2.project_lp2.services;

import org.springframework.stereotype.Service;

import com.project_lp2.project_lp2.models.ModelCustomer;
import com.project_lp2.project_lp2.repository.mapper._RepositoryCustomer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class ServiceCustomer {
    
    @Autowired
    _RepositoryCustomer repository;

    public ServiceCustomer(_RepositoryCustomer repository) {
        this.repository = repository;
    }

    public List<ModelCustomer> serv_list_customer() {
        return repository.findAll();
    }

    public void serv_create_update_customer(ModelCustomer customer) {
        repository.save(customer);
    }

    public ModelCustomer serv_get_customer(Long id) {
        return repository.findById(id).get();
    }

    public void serv_delete_customer(Long id) {
        repository.deleteById(id);
    }

    public boolean serv_exists_customer(Long id) {
        return repository.existsById(id);
    }

    public List<ModelCustomer> serv_getByName_customer(String name) {
        return repository.findByName(name);
    }
}
