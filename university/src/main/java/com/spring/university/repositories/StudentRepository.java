package com.spring.university.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring.university.pojo.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {
	public List<Student> findByDepartmentId(Integer departmentId);
}
