package com.ims.service;

import java.util.List;

import com.ims.model.UserDetails;

public interface IUserDetailsService {

	void saveUserDetails(UserDetails userDetails);

	List<UserDetails> viewUserDetailsList();

	UserDetails findByUserDetailsId(Integer userDetailsId);

	void deleteUserDetails(Integer userDetailsId);
	
}
