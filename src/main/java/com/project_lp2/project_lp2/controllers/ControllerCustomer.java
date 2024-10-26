package com.project_lp2.project_lp2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project_lp2.project_lp2.models.ModelCustomer;
import com.project_lp2.project_lp2.services.ServiceCustomer;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Controller
@RequestMapping("/customer")
public class ControllerCustomer {
	
	@Autowired
	private ServiceCustomer service;
	
	@GetMapping("/list")
	public String ctr_list_customer(Model model) {

		log.info("Entering the method 'ctr_list_customer' of the class 'ControllerCustomer' for the path '/customer/list'");

		log.info("Fetching a list of 'ModelCustomer' named 'ls_customer' from the service named 'serv_list_customer'");
		List<ModelCustomer> ls_customer = service.serv_list_customer();

		log.info("Sending a list of 'ModelCustomer' named 'ls_customer' to the template (model) through an attribute named 'customers'");
		
		if( log.isDebugEnabled() ) 
			log.debug("List: {}", ls_customer.toString() );
		
		model.addAttribute("customers", ls_customer);

		log.info("successfully returning the template named 'TempIndex' from 'ctr_list_customer' pertaining of 'ControllerCustomer' for the path '/customer/list'");
		return "/views/views_customer/TempIndex";
	}
	
	@GetMapping("/register")
	public String ctr_register_customer(Model model) {

		log.info("Entering the method 'ctr_register_customer' of the class 'ControllerCustomer' for the path '/customer/register'");

		log.info("Adding a new 'ModelCustomer' instance to the model with the attribute name 'customer'");
		model.addAttribute("customer", new ModelCustomer());
	
		log.info("Successfully returning the template named 'TempRegisterCustomer' from 'ctr_register_customer' pertaining of 'ControllerCustomer' for the path '/customer/register'");
		return "views/views_customer/TempRegisterCustomer";
	}

	
	@PostMapping("/save_customer")
	public String ctr_save_customer(@ModelAttribute ModelCustomer customer) {
	
		log.info("Entering the method 'ctr_save_customer' of the class 'ControllerCustomer' for the path '/customer/save_customer'");
		
		log.info("Saving or updating a 'ModelCustomer' instance with details: {}", customer.toString());
		service.serv_create_update_customer(customer);
	
		log.info("Successfully saved or updated the 'ModelCustomer' instance. Redirecting to '/customer/list'");
		return "redirect:/customer/list";
	}

	@GetMapping("/edit/{id}")
	public String ctr_edit_customer(@PathVariable long id, Model model) {

		log.info("Entering the method 'ctr_edit_customer' of the class 'ControllerCustomer' for the path '/customer/edit/{}'", id);
    
		log.info("Fetching 'ModelCustomer' instance with id: {}", id);
		ModelCustomer customer = service.serv_get_customer(id);

		if (customer != null) {
			log.info("Found 'ModelCustomer' instance: {}", customer.toString());
			model.addAttribute("customer", customer);
		} else 
			log.warn("No 'ModelCustomer' instance found with id: {}", id);
		

		log.info("Successfully returning the template named 'TempEditCustomer' from 'ctr_edit_customer' pertaining of 'ControllerCustomer' for the path '/customer/edit/{}'", id);
		return "views/views_customer/TempEditCustomer";
	}
	
	@PostMapping("/delete/{id}")
	public String ctr_delete_customer(@PathVariable long id) {
	
		log.info("Entering the method 'ctr_delete_customer' of the class 'ControllerCustomer' for the path '/customer/delete/{}'", id);
		
		log.info("Deleting 'ModelCustomer' instance with id: {}", id);
		service.serv_delete_customer(id);
	
		log.info("Successfully deleted the 'ModelCustomer' instance with id: {}. Redirecting to '/customer/list'", id);
		return "redirect:/customer/list";
	}

	@GetMapping("/search")
	public String ctr_search_customer(@RequestParam("customer_by_name") String customerName, Model model) {
		
		log.info("Entering the method 'ctr_search_customer' of the class 'ControllerCustomer' for the path '/customer/search'");
    
		log.info("Searching for 'ModelCustomer' instances with name containing: '{}'", customerName);
		List<ModelCustomer> ls_customer = service.serv_getByName_customer(customerName);
	
		log.info("Sending a list of 'ModelCustomer' named 'ls_customer' to the template (model) through an attribute named 'customers'");
		if (log.isDebugEnabled()) {
			log.debug("List: {}", ls_customer.toString());
		}
		model.addAttribute("customers", ls_customer);
	
		log.info("Successfully returning the template named 'TempIndex' from 'ctr_search_customer' pertaining of 'ControllerCustomer' for the path '/customer/search'");
		return "views/views_customer/TempIndex";
	}
	

}
