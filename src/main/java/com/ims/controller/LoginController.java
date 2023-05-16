package com.ims.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ims.model.AuthenticationRequest;
import com.ims.model.LoginResponse;
import com.ims.model.User;
import com.ims.security.BlockListCacheService;
import com.ims.security.JWTTokenHelper;
import com.ims.service.ICandidateService;
import com.ims.service.IUserService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	JWTTokenHelper jWTTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICandidateService canServ;
//	
	@Autowired
	private BlockListCacheService blockListCacheService;

	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest)
			throws InvalidKeySpecException, NoSuchAlgorithmException {
//		System.err.println(authenticationRequest.getUserName());

		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),
						authenticationRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		User user = (User) authentication.getPrincipal();
		String jwtToken = jWTTokenHelper.generateToken(user.getUsername());

		LoginResponse response = new LoginResponse();
		response.setToken(jwtToken);
		
		
		user.getAuthorities().forEach(authority -> 
		{
//			System.err.println("Authority => " +authority.getAuthority());
			response.setRole(authority.getAuthority());
		});
		response.setUser(user);

//		System.err.println(response);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/auth/userinfo")
	public ResponseEntity<?> getUserInfo() 
	{
		List userObj =userService.viewUserList();
		
		canServ.viewCandidateList().forEach(can ->
		{
			userObj.add(can);
		});
		
	
		return new ResponseEntity<>(userObj,HttpStatus.OK);

	}
	@GetMapping("/auth/logout/{username}/{token}")
	public ResponseEntity<?> logout(@PathVariable String username,@PathVariable String token) 
	{
//		 String status="OK";
		 System.err.println("Username => "+username);
		 System.err.println("Token => "+token);
		
		 blockListCacheService.addUserToBlockList(username,token);
		 String status="";
		 if(!blockListCacheService.isUserBlocked(username))
		 {
			 status="success";
		 }
		 else {
			status="failure";
		}
		return new ResponseEntity<>(status,HttpStatus.OK);

	}

	@GetMapping("/sample")
	public ResponseEntity<?> getsampleResposne() {
		User us = new User();
		us.setFirstName("Testing");
		us.setUserName("Pass");
		return ResponseEntity.ok(us);
	}
}
