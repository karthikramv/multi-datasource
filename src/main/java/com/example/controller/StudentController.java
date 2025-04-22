package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Student;
import com.example.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService service;
	
	@PostMapping("/create")
	public Student createStudentInfo(@RequestBody Student student) {
		return service.createStudentInfo(student);
	}
	@GetMapping("/get/all")
	public List<Student> getAllStudent() {
		return service.getAllStudent();
	}
}
