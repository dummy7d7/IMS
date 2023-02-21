package com.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ims.model.Domain;

public interface IDomainDao extends JpaRepository<Domain, Integer> {
	Domain findByDomainName(String domainName);

}
