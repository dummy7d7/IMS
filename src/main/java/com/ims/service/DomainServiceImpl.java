package com.ims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.model.Domain;
import com.ims.repository.IDomainDao;

import lombok.extern.log4j.Log4j2;
@Log4j2
@Service
public class DomainServiceImpl implements IDomainService {
	
	@Autowired
	private IDomainDao iDomainDao;
	
	@Override
	public Domain saveDomain(Domain domain) {
		log.info("new Domain added successfully");
		Domain domain1 = iDomainDao.save(domain);
		return domain1;
	}

	@Override
	public List<Domain> viewDomainList() {
		log.info("find all Domains from the database");
		return iDomainDao.findAll();
	}

	@Override
	public Domain findByDomainId(Integer domainId) {
		log.info("Domain with domainId "+domainId +" updated");
		return iDomainDao.findById(domainId).get();
	}

	@Override
	public void deleteDomain(Integer domainId) {
		iDomainDao.deleteById(domainId);
		log.info("Domain with domainId "+domainId +" deleted");
	}

}
