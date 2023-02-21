package com.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ims.model.Candidate;
import com.ims.model.Feedback;

public interface IFeedbackDao extends JpaRepository<Feedback, Integer>
{
Feedback findByCandidate(Candidate candidate);
boolean existsFeedbackByCandidate(Candidate candidate);

}
