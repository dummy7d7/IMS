package com.ims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ims.model.Candidate;
import com.ims.model.Schedule;
import com.ims.model.User;
import com.ims.repository.ICandidateDao;
import com.ims.service.ICandidateService;
import com.ims.service.IScheduleService;
import com.ims.service.IUserService;

@RestController
@CrossOrigin
@RequestMapping("/ims")
public class ScheduleController {
	@Autowired
	private IScheduleService scheduleService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICandidateService candidateService;

	@PostMapping("/schedule")
	public ResponseEntity<Schedule> addSchedule(@RequestBody Schedule scheduleRequest) 
	{
		User user=userService.findById(scheduleRequest.getUserId());
		List<Candidate> candidateList=candidateService.findAllCandidateById(scheduleRequest.getCandidateIds());
		
		scheduleRequest.setCandidate(candidateList);
		scheduleRequest.setUser(user);
		
//		System.err.println(scheduleRequest);
		Schedule schedule = scheduleService.saveSchedule(scheduleRequest);
//	return ResponseEntity<>(schedule,HttpStatus.CREATED);
		return new ResponseEntity<>(schedule, HttpStatus.CREATED);
	}

	@GetMapping("/schedules")
	public ResponseEntity<List<Schedule>> viewAllSchedule() {
		List<Schedule> scheduleList = scheduleService.viewScheduleList();
		return new ResponseEntity<>(scheduleList, HttpStatus.OK);
	}

	@GetMapping("/schedule/{id}")
	public ResponseEntity<Schedule> viewScheduleById(@PathVariable Integer id) {
		Schedule schedule = scheduleService.findByScheduleId(id);
		return new ResponseEntity<Schedule>(schedule, HttpStatus.OK);
	}

	@PutMapping("/schedule/{id}")
	public ResponseEntity<Schedule> updateSchedule(@PathVariable Integer id, @RequestBody Schedule schedule) {
		Schedule scheduleAfterSave = scheduleService.saveSchedule(schedule);
		return new ResponseEntity<Schedule>(scheduleAfterSave, HttpStatus.OK);
	}

	@DeleteMapping("/schedule/{id}")
	public ResponseEntity<String> deleteCandidate(@PathVariable Integer id) {
		scheduleService.deleteSchedule(id);
		return new ResponseEntity<String>("Schedule with id: " + id + " deleted", HttpStatus.OK);
	}
}
