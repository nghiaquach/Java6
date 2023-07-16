package com.j6d2.controller;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.j6d2.bean.Student;

@Controller
public class StudentController {
	@RequestMapping("/student")
	public String student(Model model, 
			@RequestParam("index") Optional<Integer> index) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		String path = "C:\\Users\\Admin\\Desktop\\JShop\\J6D2\\src\\main\\resources\\static\\students.json";
		TypeReference<List<Student>> type = new TypeReference<List<Student>>() {};
		List<Student> list = mapper.readValue(new File(path), type);
		
		Student student = list.get(index.orElse(0));
		model.addAttribute("sv", student);
		model.addAttribute("index", index.orElse(0));
		
		return "student";
	}
}