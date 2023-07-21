package com.j6d6.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@Autowired
	HttpServletRequest request;
	
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
		if(!request.isUserInRole("ADMIN")) {
			return "redirect:/auth/access/denied";
		}
		model.addAttribute("message", "Hello administrator");
		return "home/index";
	}
	
	@RequestMapping("/home/users")
	public String users(Model model){
		if(!(request.isUserInRole("ADMIN") || request.isUserInRole("USER"))) {
			return "redirect:/auth/access/denied";
		}
		model.addAttribute("message", "Hello staff");
		return "home/index";
	}
	
	@RequestMapping("/home/authenticated")
	public String authenticated(Model model){
		if(request.getRemoteUser() == null) {
			return "redirect:/auth/login/form";
		}
		model.addAttribute("message", "Hello authenticated user");
		return "home/index";
	}
}