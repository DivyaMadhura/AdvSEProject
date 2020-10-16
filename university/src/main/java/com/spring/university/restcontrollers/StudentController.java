package com.spring.university.restcontrollers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.university.pojo.Student;
import com.spring.university.repositories.StudentRepository;

@RestController
public class StudentController {

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Autowired
	private StudentRepository studentRepository;

	@RequestMapping(value = "/web/registerstudent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveStudentDetails(@RequestBody Student student) {
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);
		try {
			studentRepository.save(student);
			transactionManager.commit(status);
		} catch (Exception ex) {
			transactionManager.rollback(status);
		}
		return ResponseEntity.status(HttpStatus.OK).body("Success");
	}

	@RequestMapping(value = "/web/studentslist/{departmentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getStudentsList(@PathVariable("departmentId") Integer departmentId) {
		List<Student> students = new ArrayList<>();
		students = studentRepository.findByDepartmentId(departmentId);
		return ResponseEntity.status(HttpStatus.OK).body(students);
	}
}
