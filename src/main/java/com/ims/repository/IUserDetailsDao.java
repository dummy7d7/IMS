package com.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ims.model.UserDetails;

public interface IUserDetailsDao extends JpaRepository<UserDetails, Integer>
{

}
