package com.ims.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ims.model.Candidate;
import com.ims.model.Schedule;

public interface IScheduleDao extends JpaRepository<Schedule, Integer>
{
Schedule  findByCandidateIn(List<Candidate> candidate);	
boolean existsScheduleByCandidateIn(List<Candidate> candidateList);
}
