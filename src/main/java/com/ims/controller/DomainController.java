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

import com.ims.model.Domain;
import com.ims.service.IDomainService;

@RestController
@CrossOrigin
@RequestMapping("/ims")
public class DomainController {
	
	@Autowired
	private IDomainService domainService;

	@PostMapping("/domain")
	public ResponseEntity<Domain> createDomain(@RequestBody Domain domainRequest) 
	{

		Domain domain=domainService.saveDomain(domainRequest);
		return new ResponseEntity<>(domain, HttpStatus.CREATED);
	}
	
	@GetMapping("/domains")
	public ResponseEntity<List<Domain>> getAllDomain()
	{
		List<Domain> domain = domainService.viewDomainList();
		return new ResponseEntity<>(domain, HttpStatus.OK);
	}
	
	@GetMapping("/domain/{id}")
	public ResponseEntity<Domain> getDomain(@PathVariable("id") Integer id)
	{
		Domain domain = domainService.findByDomainId(id);
		return new ResponseEntity<>(domain, HttpStatus.OK);
	}
	
	@DeleteMapping("/domain/{id}")
	public ResponseEntity<String> deleteDomain(@PathVariable("id") Integer id) 
	{
		domainService.deleteDomain(id);

		return new ResponseEntity<>("Domain with id "+id+" has been deleted successfully.",HttpStatus.OK);
	}
	
	@PutMapping("/domain/{id}")
	public ResponseEntity<Domain> updateDomain( @PathVariable("id") Integer id, @RequestBody Domain domainRequest) 
	{
		Domain e= domainService.saveDomain(domainRequest);
		 return new ResponseEntity<>(e,HttpStatus.OK);
	}
	
}
