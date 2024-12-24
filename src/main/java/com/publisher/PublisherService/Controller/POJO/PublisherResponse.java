package com.publisher.PublisherService.Controller.POJO;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class PublisherResponse {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String Address;
	
	
	
}
