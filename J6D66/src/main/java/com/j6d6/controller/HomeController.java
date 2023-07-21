package com.j6d6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/home/index")
	public String index(Model model){
		model.addAttribute("message", "This is home page");
		return "home/index";
	}
	
	@RequestMapping("/home/about")
	public String about(Model model){
		model.addAttribute("message", "This is introduction page");
		return "home/index";
	}
	
	@RequestMapping("/home/admins")
	public String admins(Model model){
		model.addAttribute("message", "Hello administrator");
		return "home/index";
	}
	
	@RequestMapping("/home/users")
	public String users(Model model){
		model.addAttribute("message", "Hello staff");
		return "home/index";
	}
	
	@RequestMapping("/home/authenticated")
	public String authenticated(Model model){
		model.addAttribute("message", "Hello authenticated user");
		return "home/index";
	}
	
	@RequestMapping("/home/thymeleaf1")
	public String thymeleaf1(Model model){
		model.addAttribute("message", "Thymeleaf - Without Extras");
		return "home/thymeleaf1";
	}
	
	@RequestMapping("/home/thymeleaf2")
	public String thymeleaf2(Model model){
		model.addAttribute("message", "Thymeleaf - With Extras");
		return "home/thymeleaf2";
	}
}