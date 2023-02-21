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
import com.ims.service.ICandidateService;

@RestController
@CrossOrigin
@RequestMapping("/ims")
public class CandidateController {
	@Autowired
	private ICandidateService candidateService;
	@PostMapping("/candidate")
	public ResponseEntity<Candidate> createStudent(@RequestBody Candidate candidateRequest) 
	{

		Candidate student=candidateService.save(candidateRequest);
		return new ResponseEntity<>(student, HttpStatus.CREATED);
	}
	
	@GetMapping("/candidates")
	public ResponseEntity<List<Candidate>> getAllCandidates()
	{
		List<Candidate> student = candidateService.viewCandidateList();
//		System.out.println("Entering");
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	
	@GetMapping("/candidate/{id}")
	public ResponseEntity<Candidate> getStudent(@PathVariable("id") Integer id)
	{
		 Candidate candidate=candidateService.findCandidateById(id);
		return new ResponseEntity<>(candidate, HttpStatus.OK);
	}
	
	@DeleteMapping("/candidate/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") Integer id) 
	{
		candidateService.deleteCandidate(id);

		return new ResponseEntity<String>("Candidate with id: "+id+" deleted",HttpStatus.OK);
	}
	
	@PutMapping("/candidate/{id}")
	public ResponseEntity<Candidate> updateStudent( @PathVariable("id") Integer id, @RequestBody Candidate candidateRequest) 
	{
		Candidate candidate=candidateService.save(candidateRequest);
    return new ResponseEntity<>(candidate,HttpStatus.OK);
	}
}
