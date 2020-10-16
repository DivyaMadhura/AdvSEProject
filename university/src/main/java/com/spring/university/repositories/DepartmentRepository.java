package com.spring.university.repositories;

import org.springframework.data.repository.CrudRepository;

import com.spring.university.pojo.Department;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {

}
