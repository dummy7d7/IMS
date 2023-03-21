package com.ims.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DomainRequest 
{
	
	private Integer domainId;
	private String domainName;
	
	private String[] domainCategory; 
	private Integer[] domCatIds;

}
