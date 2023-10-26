package com.ims.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ims.model.Authority;
import com.ims.model.User;
import com.ims.repository.IAuthorityDao;
import com.ims.service.IUserService;

@RestController
@CrossOrigin
@RequestMapping("/ims")
public class UserController {
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IAuthorityDao authorityDao;
	
	@Autowired
	private PasswordEncoder passwordEn;

	@PostMapping("user")
	public ResponseEntity<User> createUser(@RequestBody User userRequest) 
	{
		List<Authority> authorityList=new ArrayList<>();
		Authority authority= authorityDao.findById(Long.parseLong(userRequest.getPhoneNumber())).orElseThrow();
		authorityList.add(authority);
		userRequest.setAuthorities( authorityList);
//		System.err.println(userRequest);
		userRequest.setEnabled(true);
		userRequest.setPassword(passwordEn.encode(userRequest.getPassword()));
//		System.err.println(userRequest);
		User user = userService.saveUser(userRequest);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@GetMapping("users")
	public ResponseEntity<List<User>> getAllUser() 
	{
		List<User> user = userService.viewUserList();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) 
	{
//		System.err.println("----");
		User user = userService.findById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@DeleteMapping("user/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id)
	{
		userService.deleteUser(id);
 
		return new ResponseEntity<>("User with id " + id + " has been deleted successfully.", HttpStatus.OK);
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User userRequest) 
	{
		List<Authority> authorityList=new ArrayList<>();
		User user=userService.findById(userRequest.getId());
		Authority authority= authorityDao.findById(Long.parseLong(userRequest.getPhoneNumber())).orElseThrow();
		authorityList.add(authority);
		userRequest.setAuthorities( authorityList);
//		System.err.println(userRequest);
		userRequest.setEnabled(true);
		if(!(userRequest.getPassword().equals(user.getPassword())));
		{
				userRequest.setPassword(passwordEn.encode(userRequest.getPassword()));	
		}
		
		
		User usr = userService.saveUser(userRequest);
		return new ResponseEntity<>(usr, HttpStatus.OK);
	}
	
	@GetMapping("/Authorities")
	public ResponseEntity<List<Authority>> getAuthorities()
	{
		List<Authority> list=authorityDao.findAll();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
}
