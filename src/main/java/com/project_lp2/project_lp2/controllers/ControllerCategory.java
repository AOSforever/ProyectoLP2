package com.project_lp2.project_lp2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;

import com.project_lp2.project_lp2.models.ModelCategory;
import com.project_lp2.project_lp2.services.ServiceCategory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/category")
public class ControllerCategory {

    @Autowired
    private ServiceCategory service;

    @GetMapping("/list")
    public String ctr_list_category(Model model) {
        log.info("Entering the method 'ctr_list_category' of the class 'ControllerCategory' for the path '/category/list'");

        log.info("Fetching a list of 'ModelCategory' from the service named 'serv_list_category'");
        List<ModelCategory> ls_categories = service.serv_list_category();

        log.info("Sending a list of 'ModelCategory' named 'ls_categories' to the template (model) through an attribute named 'categories'");
        if (log.isDebugEnabled()) {
            log.debug("List: {}", ls_categories.toString());
        }
        model.addAttribute("categories", ls_categories);

        log.info("Successfully returning the template named 'TempIndexCategory' from 'ctr_list_category' pertaining of 'ControllerCategory' for the path '/category/list'");
        return "views/views_category/TempIndexCategory";
    }

    @GetMapping("/register")
    public String ctr_register_category(Model model) {
        log.info("Entering the method 'ctr_register_category' of the class 'ControllerCategory' for the path '/category/register'");

        log.info("Adding a new 'ModelCategory' instance to the model with the attribute name 'category'");
        model.addAttribute("category", new ModelCategory());

        log.info("Successfully returning the template named 'TempRegisterCategory' from 'ctr_register_category' pertaining of 'ControllerCategory' for the path '/category/register'");
        return "views/views_category/TempRegisterCategory";
    }

    @PostMapping("/save_category")
    public String ctr_save_category(@ModelAttribute ModelCategory category) {
        log.info("Entering the method 'ctr_save_category' of the class 'ControllerCategory' for the path '/category/save_category'");

        log.info("Saving or updating a 'ModelCategory' instance with details: {}", category.toString());
        service.serv_create_update_category(category);

        log.info("Successfully saved or updated the 'ModelCategory' instance. Redirecting to '/category/list'");
        return "redirect:/category/list";
    }

    @GetMapping("/edit/{id}")
    public String ctr_edit_category(@PathVariable long id, Model model) {
        log.info("Entering the method 'ctr_edit_category' of the class 'ControllerCategory' for the path '/category/edit/{}'", id);

        log.info("Fetching 'ModelCategory' instance with id: {}", id);
        ModelCategory category_edit = service.serv_find_category(id);

        if (category_edit != null) {
            log.info("Found 'ModelCategory' instance: {}", category_edit.toString());
            model.addAttribute("category", category_edit);
        } else {
            log.warn("No 'ModelCategory' instance found with id: {}", id);
        }

        log.info("Successfully returning the template named 'TempEditCategory' from 'ctr_edit_category' pertaining of 'ControllerCategory' for the path '/category/edit/{}'", id);
        return "views/views_category/TempEditCategory";
    }

    @PostMapping("/delete/{id}")
    public String ctr_delete_category(@PathVariable long id) {
        log.info("Entering the method 'ctr_delete_category' of the class 'ControllerCategory' for the path '/category/delete/{}'", id);

        log.info("Deleting 'ModelCategory' instance with id: {}", id);
        service.serv_delete_category(id);

        log.info("Successfully deleted the 'ModelCategory' instance with id: {}. Redirecting to '/category/list'", id);
        return "redirect:/category/list";
    }
}
