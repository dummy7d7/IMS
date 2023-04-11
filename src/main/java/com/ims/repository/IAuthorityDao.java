package com.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ims.model.Authority;

public interface IAuthorityDao  extends JpaRepository<Authority, Long>{

}
