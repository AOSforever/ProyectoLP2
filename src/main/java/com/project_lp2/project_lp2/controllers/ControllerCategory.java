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

@Controller
@RequestMapping("/category")
public class ControllerCategory {

    @Autowired
    private ServiceCategory service;

    @GetMapping("/list")
    public String ctr_list_category(Model model) {

        List<ModelCategory> ls_categories = service.serv_list_category();

        model.addAttribute("categories", ls_categories);

        return "views/views_category/TempIndexCategory";
    }

    @GetMapping("/register")
    public String ctr_register_category(Model model) {

        ModelCategory category = new ModelCategory();

        model.addAttribute("category", category);

        return "views/views_category/TempRegisterCategory";
    }

    @PostMapping("/save_category")
    public String ctr_save_category(@ModelAttribute ModelCategory category) {

        service.serv_create_update_category(category);

        return "redirect:/category/list";
    }

    @GetMapping("/edit/{id}")
    public String ctr_edit_category(@PathVariable long id, Model model) {

        ModelCategory category_edit = service.serv_find_category(id);

        model.addAttribute("category", category_edit);

        return "views/views_category/TempEditCategory";
    }

    @PostMapping("/delete/{id}")
    public String ctr_delete_category(@PathVariable long id) {

        service.serv_delete_category( id );

        return "redirect:/category/list";
    }
}
