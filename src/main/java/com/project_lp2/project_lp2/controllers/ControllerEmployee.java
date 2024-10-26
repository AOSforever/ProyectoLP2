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

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/employee")
public class ControllerEmployee {

    @Autowired
    private ServiceEmployee service;

    @GetMapping("/list")
    public String ctr_list_employee(Model model) throws IOException {
        log.info("Entering the method 'ctr_list_employee' of the class 'ControllerEmployee' for the path '/employee/list'");

        ArrayList<DTOModelEmployee> ls_models_dtos = new ArrayList<>();

        log.info("Fetching a list of 'ModelEmployee' from the service named 'serv_list_employee'");
        for (ModelEmployee employee : service.serv_list_employee()) {
            DTOModelEmployee dto_employee = service.serv_builder_dto_employee(employee);
            ls_models_dtos.add(dto_employee);
        }

        log.info("Sending a list of 'DTOModelEmployee' named 'ls_models_dtos' to the template (model) through an attribute named 'employees'");
        if (log.isDebugEnabled()) {
            log.debug("List: {}", ls_models_dtos.toString());
        }
        model.addAttribute("employees", ls_models_dtos);

        log.info("Successfully returning the template named 'TempIndexEmployee' from 'ctr_list_employee' pertaining of 'ControllerEmployee' for the path '/employee/list'");
        return "views/views_employee/TempIndexEmployee";
    }

    @GetMapping("/register")
    public String ctr_register_employee(Model model) {
        log.info("Entering the method 'ctr_register_employee' of the class 'ControllerEmployee' for the path '/employee/register'");

        log.info("Adding a new 'ModelEmployee' instance to the model with the attribute name 'employee'");
        model.addAttribute("employee", new ModelEmployee());

        log.info("Successfully returning the template named 'TempRegisterEmployee' from 'ctr_register_employee' pertaining of 'ControllerEmployee' for the path '/employee/register'");
        return "views/views_employee/TempRegisterEmployee";
    }

    @PostMapping("/save_employee")
    public String ctr_save_employee(@ModelAttribute DTOModelEmployee dto_employee) throws IOException {
        log.info("Entering the method 'ctr_save_employee' of the class 'ControllerEmployee' for the path '/employee/save_employee'");

        System.out.println( dto_employee.toString() );

        log.info("Building 'ModelEmployee' instance from 'DTOModelEmployee'");
        ModelEmployee employee = service.serv_builder_model_employee(dto_employee);

        log.info("Saving or updating a 'ModelEmployee' instance with details: {}", employee.toString());
        service.serv_create_update_employee(employee);

        log.info("Successfully saved or updated the 'ModelEmployee' instance. Redirecting to '/employee/list'");
        return "redirect:/employee/list";
    }


    @GetMapping("/edit/{id}")
    public String ctr_edit_employee(@PathVariable long id, Model model) {
        log.info("Entering the method 'ctr_edit_employee' of the class 'ControllerEmployee' for the path '/employee/edit/{}'", id);

        log.info("Fetching 'ModelEmployee' instance with id: {}", id);
        ModelEmployee employee = service.serv_get_employee(id);

        if (employee != null) {
            log.info("Found 'ModelEmployee' instance: {}", employee.toString());
            DTOModelEmployee dto_employee = service.serv_builder_dto_employee(employee);
            model.addAttribute("employee", dto_employee);
        } else {
            log.warn("No 'ModelEmployee' instance found with id: {}", id);
        }

        log.info("Successfully returning the template named 'TempEditEmployee' from 'ctr_edit_employee' pertaining of 'ControllerEmployee' for the path '/employee/edit/{}'", id);
        return "views/views_employee/TempEditEmployee";
    }

    @PostMapping("/delete/{id}")
    public String ctr_delete_employee(@PathVariable long id) {
        log.info("Entering the method 'ctr_delete_employee' of the class 'ControllerEmployee' for the path '/employee/delete/{}'", id);

        log.info("Deleting 'ModelEmployee' instance with id: {}", id);
        service.serv_delete_employee(id);

        log.info("Successfully deleted the 'ModelEmployee' instance with id: {}. Redirecting to '/employee/list'", id);
        return "redirect:/employee/list";
    }
    
    @GetMapping("/search")
    public String ctr_search_employee(@RequestParam(name = "employee_by_name") String employeeName, Model model) {
        log.info("Entering the method 'ctr_search_employee' of the class 'ControllerEmployee' for the path '/employee/search'");

        log.info("Searching for 'ModelEmployee' instances with name containing: '{}'", employeeName);
        ArrayList<DTOModelEmployee> ls_models_dtos = new ArrayList<>();

        for (ModelEmployee employee : service.serv_find_employee(employeeName)) {
            DTOModelEmployee dto_employee = service.serv_builder_dto_employee(employee);
            ls_models_dtos.add(dto_employee);
        }

        log.info("Sending a list of 'DTOModelEmployee' named 'ls_models_dtos' to the template (model) through an attribute named 'employees'");
        if (log.isDebugEnabled()) {
            log.debug("List: {}", ls_models_dtos.toString());
        }
        model.addAttribute("employees", ls_models_dtos);
        model.addAttribute("static_text", employeeName);

        log.info("Successfully returning the template named 'TempIndexEmployee' from 'ctr_search_employee' pertaining of 'ControllerEmployee' for the path '/employee/search'");
        return "views/views_employee/TempIndexEmployee";
    }
}
