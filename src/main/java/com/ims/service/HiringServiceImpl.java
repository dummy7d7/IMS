package com.ims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ims.model.Hiring;
import com.ims.repository.IHiringDao;

import lombok.extern.log4j.Log4j2;
@Log4j2
@Service
@Transactional
public class HiringServiceImpl implements IHiringService{

	@Autowired
	private IHiringDao iHiringDao;
	
	@Override
	public Hiring saveHiring(Hiring hiring) 
	{
		Hiring saveHiring= iHiringDao.save(hiring);
		log.info("Hiring "+saveHiring.getHiringId() +"added successfully");
		return saveHiring;
	}

	@Override
	public List<Hiring> viewHiringList() {
		log.info("finding all Hirings from the database");
		return iHiringDao.findAllHiring();
	}

	@Override
	public Hiring findByHiringId(Integer hiringId) 
	{
		log.info("Find Hiring with hiringId: "+hiringId );
		return iHiringDao.findById(hiringId).get();
	}

	@Override
	public void deleteHiring(Integer hiringId) {
		
		iHiringDao.deleteById(hiringId);
		log.info("Hiring with hiringId: "+hiringId +" isv deleted");
	}

}
