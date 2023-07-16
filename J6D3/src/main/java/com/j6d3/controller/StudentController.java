package com.j6d3.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.j6d3.bean.Student;

@Controller
public class StudentController {
	@GetMapping("/student/form")
	public String form(Model model){
		Student student = new Student();
		student.setFullname("Nguyễn Văn Tèo");
		student.setCountry("VN");
		student.setGender(true);
		model.addAttribute("sv", student);
		return "student/form";
	}
	
	@PostMapping("/student/save")
	public String save(@ModelAttribute("sv") Student form){
		return "student/success";
	}
	
	@ModelAttribute("countries")
	public Map<String, String> getCountries(){
		Map<String, String> map = new HashMap<>();
		map.put("VN", "Việt Nam");
		map.put("US", "United States");
		return map;
	}
}
