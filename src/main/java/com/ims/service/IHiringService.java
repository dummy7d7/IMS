package com.ims.service;

import java.util.List;

import com.ims.model.Hiring;

public interface IHiringService {

	Hiring saveHiring(Hiring hiring);

	List<Hiring> viewHiringList();

	Hiring updateHiring(Integer hiringId);

	void deleteHiring(Integer hiringId);

}