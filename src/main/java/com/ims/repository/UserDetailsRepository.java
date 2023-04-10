package com.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ims.model.User;



public interface UserDetailsRepository extends JpaRepository<User, Long> {

	User findByUserName(String userName);
	
}
