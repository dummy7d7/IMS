package com.ims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.model.User;
import com.ims.repository.IuserDao;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IuserDao iUserDao;

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		log.info("find User from the database with id:" + id);
		return iUserDao.findById(id).orElseThrow(null);
	}

	@Override
	public User login(String userName, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsUserByUsernameAndPassword(String name, String password) {
		// TODO Auto-generated method stub

		return iUserDao.existsUserByEmailAndPassword(name, password);
	}

	@Override
	public User saveUser(User user) {
		User save = iUserDao.save(user);
		log.info("New User " + save.getId() + " added successfully");
		return save;
	}

	@Override
	public List<User> viewUserList() {
		log.info("find all User from the database");
		return iUserDao.findAll();
	}

	@Override
	public User findByUserId(Integer userId) {
		log.info("Finding User with userId: " + userId);
		return iUserDao.findById(userId).get();
	}

	@Override
	public void deleteUser(Integer userId) {
		iUserDao.deleteById(userId);
		log.info("User with userId " + userId + " deleted");
	}

	@Override
	public User findUsername(String name) {
		log.info("find User from the database with Username:" + name);
		return iUserDao.findByEmail(name);
	}

	@Override
	public boolean existsUserByEmail(String email) {

		return iUserDao.existsUserByEmail(email);
	}

}
