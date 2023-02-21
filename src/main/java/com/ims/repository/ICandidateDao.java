package com.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ims.model.Candidate;

public interface ICandidateDao extends JpaRepository<Candidate, Integer>
{

}
