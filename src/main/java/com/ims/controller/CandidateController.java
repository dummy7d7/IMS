package com.ims.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ims.model.Candidate;
import com.ims.model.Domain;
import com.ims.model.User;
import com.ims.service.ICandidateService;
import com.ims.service.IUserService;

@RestController
@CrossOrigin
@RequestMapping("/ims")
public class CandidateController 
{
	@Autowired
	private ICandidateService candidateService;
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("/candidate")
	public ResponseEntity<Candidate> createStudent(@ModelAttribute Candidate candidate) throws IOException 
	{
//		System.err.println(candidate.getDomain());
//		candidate.setDomain(null);
//		User user= userService.findById(15);
		candidate.setUser(null);
		if(!(candidate.getFile()==null))
		{
			candidate.setResume(candidate.getFile().getBytes());
			candidate.setResumeName(candidate.getFile().getOriginalFilename());
		}
		candidate.setFile(null);
		System.out.println(candidate);
		Candidate can=candidateService.save(candidate);
		return new ResponseEntity<>(candidate, HttpStatus.CREATED);
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
	public ResponseEntity<Candidate> updateStudent( @PathVariable("id") Integer id, @ModelAttribute Candidate candidateRequest) throws IOException
	{
		if(!(candidateRequest.getFile()==null))
		{
			candidateRequest.setResume(candidateRequest.getFile().getBytes());
			candidateRequest.setResumeName(candidateRequest.getFile().getOriginalFilename());
		}
		candidateRequest.setFile(null);
		Candidate candidate=candidateService.save(candidateRequest);
    return new ResponseEntity<>(candidate,HttpStatus.OK);
	}
}

