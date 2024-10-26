package com.project_lp2.project_lp2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project_lp2.project_lp2.models.ModelSupplier;
import com.project_lp2.project_lp2.services.ServiceSupplier;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/supplier")
public class ControllerSupplier {

    @Autowired
    private ServiceSupplier service;

    @GetMapping("/list")
    public String ctr_list_supplier(Model model) {
        log.info("Entering the method 'ctr_list_supplier' of the class 'ControllerSupplier' for the path '/supplier/list'");

        log.info("Fetching a list of 'ModelSupplier' from the service named 'serv_list_supplier'");
        List<ModelSupplier> ls_suppliers = service.serv_list_supplier();

        log.info("Sending a list of 'ModelSupplier' named 'ls_suppliers' to the template (model) through an attribute named 'suppliers'");
        if (log.isDebugEnabled()) {
            log.debug("List: {}", ls_suppliers.toString());
        }
        model.addAttribute("suppliers", ls_suppliers);

        log.info("Successfully returning the template named 'TempIndexSupplier' from 'ctr_list_supplier' pertaining of 'ControllerSupplier' for the path '/supplier/list'");
        return "views/views_supplier/TempIndexSupplier";
    }

    @GetMapping("/register")
    public String ctr_register_supplier(Model model) {
        log.info("Entering the method 'ctr_register_supplier' of the class 'ControllerSupplier' for the path '/supplier/register'");

        log.info("Adding a new 'ModelSupplier' instance to the model with the attribute name 'supplier'");
        model.addAttribute("supplier", new ModelSupplier());

        log.info("Successfully returning the template named 'TempRegisterSupplier' from 'ctr_register_supplier' pertaining of 'ControllerSupplier' for the path '/supplier/register'");
        return "views/views_supplier/TempRegisterSupplier";
    }

    @PostMapping("/save_supplier")
    public String ctr_save_supplier(@ModelAttribute ModelSupplier supplier) {
        log.info("Entering the method 'ctr_save_supplier' of the class 'ControllerSupplier' for the path '/supplier/save_supplier'");

        log.info("Saving or updating a 'ModelSupplier' instance with details: {}", supplier.toString());
        service.serv_create_update_supplier(supplier);

        log.info("Successfully saved or updated the 'ModelSupplier' instance. Redirecting to '/supplier/list'");
        return "redirect:/supplier/list";
    }

    @GetMapping("/edit/{id}")
    public String ctr_edit_supplier(@PathVariable long id, Model model) {
        log.info("Entering the method 'ctr_edit_supplier' of the class 'ControllerSupplier' for the path '/supplier/edit/{}'", id);

        log.info("Fetching 'ModelSupplier' instance with id: {}", id);
        ModelSupplier supplier = service.serv_get_supplier(id);

        if (supplier != null) {
            log.info("Found 'ModelSupplier' instance: {}", supplier.toString());
            model.addAttribute("supplier", supplier);
        } else {
            log.warn("No 'ModelSupplier' instance found with id: {}", id);
        }

        log.info("Successfully returning the template named 'TempEditSupplier' from 'ctr_edit_supplier' pertaining of 'ControllerSupplier' for the path '/supplier/edit/{}'", id);
        return "views/views_supplier/TempEditSupplier";
    }

    @PostMapping("/delete/{id}")
    public String ctr_delete_supplier(@PathVariable long id) {
        log.info("Entering the method 'ctr_delete_supplier' of the class 'ControllerSupplier' for the path '/supplier/delete/{}'", id);

        log.info("Deleting 'ModelSupplier' instance with id: {}", id);
        service.serv_delete_supplier(id);

        log.info("Successfully deleted the 'ModelSupplier' instance with id: {}. Redirecting to '/supplier/list'", id);
        return "redirect:/supplier/list";
    }

    @GetMapping("/search")
    public String ctr_find_supplier(@RequestParam(name = "supplier_by_name") String name, Model model) {
        log.info("Entering the method 'ctr_find_supplier' of the class 'ControllerSupplier' for the path '/supplier/search'");

        log.info("Searching for 'ModelSupplier' instances with name containing: '{}'", name);
        List<ModelSupplier> ls_suppliers = service.serv_find_supplier(name);

        log.info("Sending a list of 'ModelSupplier' named 'ls_suppliers' to the template (model) through an attribute named 'suppliers'");
        if (log.isDebugEnabled()) {
            log.debug("List: {}", ls_suppliers.toString());
        }
        model.addAttribute("suppliers", ls_suppliers);
        model.addAttribute("static_text", name);

        log.info("Successfully returning the template named 'TempIndexSupplier' from 'ctr_find_supplier' pertaining of 'ControllerSupplier' for the path '/supplier/search'");
        return "views/views_supplier/TempIndexSupplier";
    }
}
