package com.ims.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ims.model.User;

@Service
public interface IUserService {
	User findById(int id);

	User login(String userName, String password);

	boolean existsUserByUsernameAndPassword(String name, String password);

	User saveUser(User user);

	List<User> viewUserList();

	User findByUserId(Integer userId);

	void deleteUser(Integer userId);

	User findUsername(String name);

	boolean existsUserByEmail(String email);
}
