package com.spring.university.repositories;

import org.springframework.data.repository.CrudRepository;

import com.spring.university.pojo.UserDetails;

public interface UserDetailsRepository extends CrudRepository<UserDetails, Integer> {

	public UserDetails findByUsernameAndPassword(String username, String password);
}
