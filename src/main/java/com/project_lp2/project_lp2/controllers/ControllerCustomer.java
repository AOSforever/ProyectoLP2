package com.project_lp2.project_lp2.controllers;

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


@Controller
@RequestMapping("/customer")
@Slf4j
public class ControllerCustomer {
	
	@Autowired
	private ServiceCustomer service;
	
	@GetMapping("/list")
	public String ctr_list_customer(Model model) {

		log.info("Listando todos los clientes");

		model.addAttribute("customers", service.serv_list_customer() );

		return "/views/views_customer/TempIndex";
	}
	
	@GetMapping("/register")
	public String ctr_register_customer(Model model) {

		model.addAttribute("customer", new ModelCustomer() );

		return "views/views_customer/TempRegisterCustomer";
	}

	
	@PostMapping("/save_customer")
	public String ctr_save_customer(@ModelAttribute ModelCustomer customer) {

		service.serv_create_update_customer( customer );

		return "redirect:/customer/list";
	}

	@GetMapping("/edit/{id}")
	public String ctr_edit_customer(@PathVariable long id, Model model) {

		ModelCustomer customer = service.serv_get_customer(id);

		model.addAttribute("customer", customer);

		return "views/views_customer/TempEditCustomer";
	}
	
	@PostMapping("/delete/{id}")
	public String ctr_delete_customer(@PathVariable long id) {

		service.serv_delete_customer(id);

		return "redirect:/customer/list";
	}

	@GetMapping("/search")
	public String ctr_search_customer(@RequestParam("customer_by_name") String customerName, Model model) {
		
		model.addAttribute("customers", service.serv_getByName_customer( customerName ) );
		model.addAttribute("static_text", customerName);

		return "views/views_customer/TempIndex";
	}
	

}
