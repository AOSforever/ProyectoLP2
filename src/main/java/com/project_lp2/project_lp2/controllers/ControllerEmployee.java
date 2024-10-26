package com.project_lp2.project_lp2.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project_lp2.project_lp2.dtos.DTOModelEmployee;
import com.project_lp2.project_lp2.models.ModelEmployee;
import com.project_lp2.project_lp2.services.ServiceEmployee;
import com.project_lp2.project_lp2.services.ServiceMultimedia;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employee")
public class ControllerEmployee {

    private ServiceMultimedia service_mul = new ServiceMultimedia();

    @Autowired
    private ServiceEmployee service;

    @GetMapping("/list")
    public String ctr_list_employee(Model model) throws IOException {

        ArrayList<DTOModelEmployee> ls_models_dtos = new ArrayList<>();

        for( ModelEmployee employee : service.serv_list_employee() ) {
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

            ls_models_dtos.add( dto_employee );
        }

        model.addAttribute("employees", ls_models_dtos );

        return "views/views_employee/TempIndex";
    }

    @GetMapping("/register")
    public String ctr_register_employee(Model model) {

        model.addAttribute("employee", new ModelEmployee() );

        return "views/views_employee/TempRegisterEmployee";
    }

    @PostMapping("/save_employee")  
    public String ctr_save_employee(@ModelAttribute DTOModelEmployee dto_employee) throws IOException {

        byte[] bytes_image = service_mul.serv_convert_image_URL( dto_employee.getEmployeeUserProfile() );

        ModelEmployee employee = ModelEmployee.builder()
            .employeeDNI( dto_employee.getEmployeeDNI() )
            .employeeName( dto_employee.getEmployeeName() )
            .employeeLastName( dto_employee.getEmployeeLastName() )
            .employeePhone( dto_employee.getEmployeePhone() )
            .employeeEmail( dto_employee.getEmployeeEmail() )
            .employeeAddress( dto_employee.getEmployeeAddress() )
            .employeeUserType( dto_employee.getEmployeeUserType() )
            .employeeUserName( dto_employee.getEmployeeUserName() )
            .employeeUserPassword( dto_employee.getEmployeeUserPassword() )
            .employeeUserProfile( bytes_image )
            .build();
        
        service.serv_create_update_employee( employee );
        
        return "redirect:/employee/list";
    }
    

    @GetMapping("/edit/{id}")
    public String ctr_edit_employee(@PathVariable long id, Model model) {

        model.addAttribute("employee", service.serv_get_employee(id) );

        return "views/views_employee/TempEditEmployee";
    }

    @PostMapping("/delete/{id}")
    public String ctr_delete_employee(Model model, @PathVariable long id) {

        service.serv_delete_employee(id);

        return "redirect:/employee/list";
    }

    @GetMapping("/search")
    public String ctr_search_employee(@RequestParam(name = "employee_by_name") String employeeName, Model model) {

        model.addAttribute("employees", service.serv_find_employee(employeeName) );
        model.addAttribute("static_text", employeeName);

        return "views/views_employee/TempIndex";
    }
}
