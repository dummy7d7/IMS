package com.ims.service;

import java.util.List;

import com.ims.model.Candidate;
import com.ims.model.Feedback;

public interface IFeedbackService {

	Feedback saveFeedback(Feedback feedback);

	Object viewFeedbackList();

	Feedback findByFeedbackId(Integer feedbackId);

	void deleteFeedback(Integer feedbackId);

	Feedback getDetailsById(Integer id);

	List<Feedback> getAllFeedback();

	boolean existsFeedbackByCandidate(Candidate candidate);

	Feedback findByCandidate(Candidate candidate);

	Feedback updateInterviewerFbStatus(Integer id, String status);

}
