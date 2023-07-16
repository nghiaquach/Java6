package com.j6d2.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	String name;
	Boolean gender = false;
	Double marks = 0.0;
	Contact contact;
	List<String> subjects;
}