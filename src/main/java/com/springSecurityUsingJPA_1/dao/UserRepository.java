package com.springSecurityUsingJPA_1.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.springSecurityUsingJPA_1.model.User;


public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUserName(String userName);

}
