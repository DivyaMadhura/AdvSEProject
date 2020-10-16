package com.spring.university.restcontrollers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.university.pojo.Department;
import com.spring.university.repositories.DepartmentRepository;

@RestController
public class DepartmentController {

	@Autowired
	private DepartmentRepository departmentRepository;

	@RequestMapping(value = "/web/departments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getDepartments() {
		List<Department> departments = new ArrayList<>();
		Iterable<Department> it = departmentRepository.findAll();
		departments = StreamSupport.stream(it.spliterator(), false).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(departments);
	}

}
