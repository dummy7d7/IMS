package com.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ims.model.User;

public interface IuserDao extends JpaRepository<User, Integer>
{
	
	boolean existsUserByEmailAndPassword(String name,String password);
	User findByEmail(String name);
	boolean existsUserByEmail(String email);
	
}
