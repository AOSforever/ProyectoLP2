package com.project_lp2.project_lp2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project_lp2.project_lp2.models.ModelSupplier;
import com.project_lp2.project_lp2.services.ServiceSupplier;
import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;





@Controller
@RequestMapping("/supplier")
public class ControllerSupplier {

    @Autowired
    private ServiceSupplier service;

    @GetMapping("/list")
    public String ctr_list_supplier(Model model) {

        List<ModelSupplier> ls_suppliers = service.serv_list_supplier();

        model.addAttribute("suppliers", ls_suppliers);

        return "views/views_supplier/TempIndexSupplier";
    }

    @GetMapping("/register")
    public String ctr_register_supplier(Model model) {

        ModelSupplier supplier = new ModelSupplier();

        model.addAttribute("supplier", supplier);

        return "views/views_supplier/TempRegisterSupplier";
    }

    @PostMapping("/save_supplier")
    public String ctr_save_supllier(@ModelAttribute ModelSupplier supplier) {

        service.serv_create_update_supplier(supplier);

        return "redirect:/supplier/list";
    }

    @GetMapping("/edit/{id}") 
    public String ctr_edit_supplier(@PathVariable long id, Model model) {

        ModelSupplier supplier = service.serv_get_supplier(id);

        model.addAttribute("supplier", supplier);

        return "views/views_supplier/TempEditSupplier";
    }
    
    @PostMapping("/delete/{id}")
    public String ctr_delete_supplier(@PathVariable long id) {

        service.serv_delete_supplier(id);
        
        return "redirect:/supplier/list";
    }
    
    @GetMapping("/search")
    public String ctr_find_supplier(@RequestParam(name = "supplier_by_name") String name, Model model) {
        
        List<ModelSupplier> ls_suppliers = service.serv_find_supplier(name);
        
        model.addAttribute("suppliers", ls_suppliers);
        model.addAttribute("static_text", name);
        
        return "views/views_supplier/TempIndexSupplier";
    }
}
