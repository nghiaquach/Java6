package com.j6d7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.j6d7.service.SecurityRestTemplate;
import com.j6d7.service.SecurityRestURL;

@Controller
public class AuthorizeController{
	String url = "http://localhost:8080/rest/authorities";
	
	@Autowired
	SecurityRestTemplate restTemplate;
	
	@GetMapping("/rest/template")
	public String demo1(Model model) {
		model.addAttribute("db", restTemplate.get(url));
		return "view";
	}
	
	@GetMapping("/rest/url")
	public String demo2(Model model) {
		model.addAttribute("db", SecurityRestURL.get(url));
		return "view";
	}
}
