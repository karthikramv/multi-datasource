package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Student;
import com.example.repository.StudentRepo;

@Service
public class StudentService {

	@Autowired
	private StudentRepo repo;
	
	public Student createStudentInfo(Student student) {
		return repo.save(student);
	}
	
	public List<Student> getAllStudent() {
		return repo.findAll();
	}
}
