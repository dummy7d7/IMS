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

import com.ims.model.Feedback;
import com.ims.model.Schedule;
import com.ims.service.IFeedbackService;

@RestController
@CrossOrigin
@RequestMapping("/ims")
public class FeedBackController {
	@Autowired
	private IFeedbackService feedbackService;

	@PostMapping("/feedback")
	public ResponseEntity<Feedback> addFeedback(@RequestBody Feedback feedback) {
		Feedback feedbackAfterSave = feedbackService.saveFeedback(feedback);
		return new ResponseEntity<Feedback>(feedbackAfterSave, HttpStatus.CREATED);
	}

	@GetMapping("/feedback/{id}")
	public ResponseEntity<Feedback> viewFeedbackById(@PathVariable Integer id) {
		Feedback feedback = feedbackService.findByFeedbackId(id);
		return new ResponseEntity<Feedback>(feedback, HttpStatus.OK);
	}

	@GetMapping("/feedbacks")
	public ResponseEntity<List<Feedback>> viewAllFeedbacks() {
		List<Feedback> listOfFeedback = feedbackService.getAllFeedback();
		return new ResponseEntity<List<Feedback>>(listOfFeedback, HttpStatus.OK);
	}

	@PutMapping("/feedback/{id}")
	public ResponseEntity<Feedback> updateFeedback(@PathVariable Integer id, @RequestBody Feedback feedback) {
		Feedback feedbackAfterSave = feedbackService.saveFeedback(feedback);
		return new ResponseEntity<Feedback>(feedbackAfterSave, HttpStatus.OK);
	}

	@DeleteMapping("/feedback/{id}")
	public ResponseEntity<String> deleteFeedback(@PathVariable Integer id) {
		feedbackService.deleteFeedback(id);
		return new ResponseEntity<String>("Feedback with id:"+id+" deleted",HttpStatus.OK);
	}
}
