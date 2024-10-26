package com.project_lp2.project_lp2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project_lp2.project_lp2.models.ModelEmployee;
import com.project_lp2.project_lp2.repository.mapper._repositoryEmployee;

@Service
public class ServiceEmployee {

    @Autowired
    _repositoryEmployee repository;

    public ServiceEmployee(_repositoryEmployee repository) {
        this.repository = repository;
    }

    public List<ModelEmployee> serv_list_employee() {
        // System.out.println( repository.findAll() );

        for( ModelEmployee employee : repository.findAll() ) {
            System.out.println( employee.toString() );
        }

        return repository.findAll();
    }

    public ModelEmployee serv_get_employee(long id) {
        return repository.findById(id).get();
    }

    public void serv_create_update_employee(ModelEmployee employee) {
        repository.save(employee);
    }

    public void serv_delete_employee(long id) {
        repository.deleteById(id);
    }

    public List<ModelEmployee> serv_find_employee(String name) {
        return repository.findByName(name);
    }
}
