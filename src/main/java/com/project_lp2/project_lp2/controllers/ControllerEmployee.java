package com.project_lp2.project_lp2.controllers;

import java.io.IOException;
import java.util.ArrayList;

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

    @Autowired
    private ServiceEmployee service;

    @GetMapping("/list")
    public String ctr_list_employee(Model model) throws IOException {

        ArrayList<DTOModelEmployee> ls_models_dtos = new ArrayList<>();

        for( ModelEmployee employee : service.serv_list_employee() ) {
            DTOModelEmployee dto_employee = service.serv_builder_dto_employee(employee);
            ls_models_dtos.add( dto_employee );
        }

        model.addAttribute("employees", ls_models_dtos );

        return "views/views_employee/TempIndexEmployee";
    }

    @GetMapping("/register")
    public String ctr_register_employee(Model model) {

        model.addAttribute("employee", new ModelEmployee() );

        return "views/views_employee/TempRegisterEmployee";
    }

    @PostMapping("/save_employee")  
    public String ctr_save_employee(@ModelAttribute DTOModelEmployee dto_employee) throws IOException {

        ModelEmployee employee = service.serv_builder_model_employee(dto_employee);
        service.serv_create_update_employee( employee );
        
        return "redirect:/employee/list";
    }
    

    @GetMapping("/edit/{id}")
    public String ctr_edit_employee(@PathVariable long id, Model model) {
        ModelEmployee employee = service.serv_get_employee(id);

        DTOModelEmployee dto_employee = service.serv_builder_dto_employee(employee);

        model.addAttribute("employee", dto_employee );

        return "views/views_employee/TempEditEmployee";
    }

    @PostMapping("/delete/{id}")
    public String ctr_delete_employee(Model model, @PathVariable long id) {

        service.serv_delete_employee(id);

        return "redirect:/employee/list";
    }

    @GetMapping("/search")
    public String ctr_search_employee(@RequestParam(name = "employee_by_name") String employeeName, Model model) {

        ArrayList<DTOModelEmployee> ls_models_dtos = new ArrayList<>();
        
        for( ModelEmployee employee : service.serv_find_employee(employeeName) ) {
            DTOModelEmployee dto_employee = service.serv_builder_dto_employee(employee);
            ls_models_dtos.add( dto_employee );
        }

        model.addAttribute("employees", ls_models_dtos );
        model.addAttribute("static_text", employeeName);

        return "views/views_employee/TempIndexEmployee";
    }
}
