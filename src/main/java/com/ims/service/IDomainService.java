package com.ims.service;

import java.util.List;

import com.ims.model.Domain;

public interface IDomainService {

	void saveDomain(Domain domain);

	List<Domain> viewDomainList();

	Domain findByDomainId(Integer domainId);

	void deleteDomain(Integer domainId);
}