package com.project_lp2.project_lp2.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project_lp2.project_lp2.dtos.DTOModelEmployee;
import com.project_lp2.project_lp2.models.ModelEmployee;
import com.project_lp2.project_lp2.repository.mapper._repositoryEmployee;

@Service
public class ServiceEmployee {

    private ServiceMultimedia service_mul = new ServiceMultimedia();

    @Autowired
    _repositoryEmployee repository;

    public ServiceEmployee(_repositoryEmployee repository) {
        this.repository = repository;
    }

    public List<ModelEmployee> serv_list_employee() {
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

    public DTOModelEmployee serv_builder_dto_employee(ModelEmployee employee) {
        DTOModelEmployee dto_employee = DTOModelEmployee.builder()
            .idEmployee( employee.getIdEmployee() )
            .employeeDNI( employee.getEmployeeDNI() )
            .employeeName( employee.getEmployeeName() )
            .employeeLastName( employee.getEmployeeLastName() )
            .employeePhone( employee.getEmployeePhone() )
            .employeeEmail( employee.getEmployeeEmail() )
            .employeeAddress( employee.getEmployeeAddress() )
            .employeeUserType( employee.getEmployeeUserType() )
            .employeeUserName( employee.getEmployeeUserName() )
            .employeeUserPassword( employee.getEmployeeUserPassword() )
            .employeeUserProfile( service_mul.serv_convert_base_image( employee.getEmployeeUserProfile(), "jpg") )
            .build();

        return dto_employee;
    }

    public ModelEmployee serv_builder_model_employee(DTOModelEmployee dto_employee) throws IOException {
        byte[] bytes_image = service_mul.serv_convert_image_URL( dto_employee.getEmployeeUserProfile() );

        ModelEmployee employee = ModelEmployee.builder()
            .idEmployee( dto_employee.getIdEmployee() )
            .employeeDNI( dto_employee.getEmployeeDNI() )
            .employeeName( dto_employee.getEmployeeName() )
            .employeeLastName( dto_employee.getEmployeeLastName() )
            .employeePhone( dto_employee.getEmployeePhone() )
            .employeeEmail( dto_employee.getEmployeeEmail() )
            .employeeAddress( dto_employee.getEmployeeAddress() )
            .employeeUserType( dto_employee.getEmployeeUserType() )
            .employeeUserName( dto_employee.getEmployeeUserName() )
            .employeeUserPassword( dto_employee.getEmployeeUserPassword() )
            .employeeUserProfile( bytes_image == null ? serv_get_employee( dto_employee.getIdEmployee() ).getEmployeeUserProfile() : bytes_image )
            .build();

        return employee;
    }
}
