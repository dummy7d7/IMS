package com.ims.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import com.ims.model.DomainCategory;
import com.ims.model.DomainRequest;
import com.ims.repository.IDomainCategoryDao;
import com.ims.service.IDomainService;

@RestController
@CrossOrigin
@RequestMapping("/ims")
public class DomainController {

	@Autowired
	private IDomainService domainService;
	
	@Autowired
	private IDomainCategoryDao domainCatDao;

	@PostMapping("/domain")
	public ResponseEntity<Domain> createDomain(@Valid  @RequestBody DomainRequest domainRequest) 
	{ 
//		System.err.println(domainRequest.getDomainName());
//		System.err.println(domainRequest.toString());
		
		Domain domain=new Domain();
		List<DomainCategory> arr = new ArrayList<DomainCategory>();

		
		List list = Arrays.asList(domainRequest.getDomainCategory());
//		System.out.println(list);
		domain.setDomainCategory(list);
		for (Object str : list) 
		{
			DomainCategory domCat = new DomainCategory();
			domCat.setDomSubCatName((String) str);
			DomainCategory dom = domainCatDao.save(domCat);
			arr.add(dom);
		}
		domain.setDomainCategory(arr);
//		System.out.println(arr);
		domain.setDomainName(domainRequest.getDomainName());
		Domain dom=domainService.saveDomain(domain);
		return new ResponseEntity<>(dom, HttpStatus.CREATED);
	}

	@GetMapping("/domains")
	public ResponseEntity<List<Domain>> getAllDomain() {
		List<Domain> domain = domainService.viewDomainList();
		return new ResponseEntity<>(domain, HttpStatus.OK);
	}

	@GetMapping("/domain/{id}")
	public ResponseEntity<DomainRequest> getDomain(@PathVariable("id") Integer id) 
	{
		Domain domain = domainService.findByDomainId(id);
		DomainRequest domRq=new DomainRequest();
		
		
		String[] arr=new String[domain.getDomainCategory().size()];
		Integer[] a=new Integer[domain.getDomainCategory().size()];
		
		int i=0;
		for (DomainCategory domCat : domain.getDomainCategory())
		{
			arr[i]=domCat.getDomSubCatName();
			a[i]=domCat.getDomSubCatId();
			
			i++;
		}
		domRq.setDomainId(domain.getDomainId());
		domRq.setDomainName(domain.getDomainName());
		domRq.setDomCatIds(a);
		domRq.setDomainCategory(arr);
		
//		System.out.println();
//		System.out.println("=>  "+domain);
		return new ResponseEntity<>(domRq, HttpStatus.OK);
	}

	@DeleteMapping("/domain/{id}")
	public ResponseEntity<String> deleteDomain(@PathVariable("id") Integer id) {
		domainService.deleteDomain(id);

		return new ResponseEntity<>("Domain with id " + id + " has been deleted successfully.", HttpStatus.OK);
	}

	@PutMapping("/domain/{id}")
	public ResponseEntity<DomainRequest> updateDomain(@PathVariable("id") Integer id, @RequestBody DomainRequest domainRequest) 
	{
//		System.err.println(domainRequest);
		Domain domain=new Domain();
		List<DomainCategory> arr = new ArrayList<DomainCategory>();

		
//		List list = Arrays.asList(domainRequest.getDomainCategory());
//		System.out.println(list);
//		domain.setDomainCategory(list);
		String[] subCatName=domainRequest.getDomainCategory();
		Integer[] subCatId=domainRequest.getDomCatIds();
		
		int size=domainRequest.getDomainCategory().length;
		for (int i=0;i<size;i++) 
		{
			int idSize=domainRequest.getDomainCategory().length - domainRequest.getDomCatIds().length;
			if(idSize==0)
			{
				DomainCategory domCat = new DomainCategory();
				domCat.setDomSubCatName(subCatName[i]); 
				domCat.setDomSubCatId(subCatId[i]);
				DomainCategory dom = domainCatDao.save(domCat);
				arr.add(dom);
			}
			else
			{
				DomainCategory domCat = new DomainCategory();
				domCat.setDomSubCatName(subCatName[i]); 
				if(i<domainRequest.getDomCatIds().length)
				{
					domCat.setDomSubCatId(subCatId[i]);
				}
				else
				{
					domCat.setDomSubCatId(null);
				}
				
				DomainCategory dom = domainCatDao.save(domCat);
				arr.add(dom);
			}

		}
		domain.setDomainCategory(arr);
//		System.out.println(arr);
		domain.setDomainName(domainRequest.getDomainName());
		domain.setDomainId(domainRequest.getDomainId());
		Domain dom=domainService.saveDomain(domain);
//		System.err.println(dom);
		
		return new ResponseEntity<>(domainRequest, HttpStatus.OK);
	}

}
