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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ims.model.Candidate;
import com.ims.repository.ICandidateDao;
import com.ims.service.ICandidateService;
import com.ims.service.IUserService;

@RestController
@CrossOrigin
@RequestMapping("/ims")
public class CandidateController {
	@Autowired
	private ICandidateService candidateService;
	
	@PostMapping("/candidate")
	public ResponseEntity<Candidate> addCandidate(@ModelAttribute Candidate candidate) throws IOException {
//		System.err.println(candidate.getDomain());
//		candidate.setDomain(null);
//		User user= userService.findById(15);
		candidate.setUser(null);
		if (!(candidate.getFile() == null)) {
			candidate.setResume(candidate.getFile().getBytes());
			candidate.setResumeName(candidate.getFile().getOriginalFilename());
		}
		candidate.setFile(null);
//		System.out.println(candidate);
		Candidate can = candidateService.save(candidate);
		return new ResponseEntity<>(can, HttpStatus.CREATED);
	}

	@PostMapping("candidates/byCandidateId")
	public ResponseEntity<List<Candidate>> getCandidatesByIds(@RequestBody List<Integer> idArray) {
		List<Candidate> listOfCandi = candidateService.findAllCandidateById(idArray);
//		System.out.println(idArray);
		return new ResponseEntity<List<Candidate>>(listOfCandi, HttpStatus.OK);
	}

	@GetMapping("/candidates")
	public ResponseEntity<List<Candidate>> getAllCandidates() {
		List<Candidate> student = candidateService.viewCandidateList();
//		System.out.println("Entering");
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@GetMapping("/candidate/{id}")
	public ResponseEntity<Candidate> getStudent(@PathVariable("id") Integer id) {
		Candidate candidate = candidateService.findCandidateById(id);
		return new ResponseEntity<>(candidate, HttpStatus.OK);
	}

	@DeleteMapping("/candidate/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") Integer id) {
		candidateService.deleteCandidate(id);

		return new ResponseEntity<String>("Candidate with id: " + id + " deleted", HttpStatus.OK);
	}

	@PutMapping("/candidate/{id}")
	public ResponseEntity<Candidate> updateStudent(@PathVariable("id") Integer id,
			@ModelAttribute Candidate candidateRequest) throws IOException {
		if (!(candidateRequest.getFile() == null)) {
			candidateRequest.setResume(candidateRequest.getFile().getBytes());
			candidateRequest.setResumeName(candidateRequest.getFile().getOriginalFilename());
		}
		candidateRequest.setFile(null);
		Candidate candidate = candidateService.save(candidateRequest);
		return new ResponseEntity<>(candidate, HttpStatus.OK);
	}
	
	@PutMapping("/candidatestatus/{id}/{status}")
	public ResponseEntity<Candidate> updateCandidateStatus(@PathVariable("id") Integer id,@PathVariable("status") String status)
	{
		
		Candidate updtCan =candidateService.findCandidateById(id);
		updtCan.setStatus(status);
//		System.err.println(updtCan.getStatus());
//		System.err.println("ID => "+id);
		Candidate candidate=candidateService.updateCandidateStatus(updtCan);
//		System.err.println("Status => "+ candidate.getStatus());
		return new ResponseEntity<>(candidate,HttpStatus.OK);
	}
}
