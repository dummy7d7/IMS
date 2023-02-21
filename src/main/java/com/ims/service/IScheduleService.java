package com.ims.service;

import java.util.List;

import com.ims.model.Candidate;
import com.ims.model.Schedule;

public interface IScheduleService {

	Schedule saveSchedule(Schedule schedule);

	List<Schedule> viewScheduleList();

	Schedule findByScheduleId(Integer scheduleId);

	void deleteSchedule(Integer scheduleId);
	
	Schedule  findByCandidate(List<Candidate> candidate);	
	
	boolean existsScheduleByCandidate(List<Candidate> candidateList);
}
