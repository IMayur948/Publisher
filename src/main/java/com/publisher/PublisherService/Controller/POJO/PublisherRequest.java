package com.publisher.PublisherService.Controller.POJO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
@NoArgsConstructor(force =true)
public class PublisherRequest {
	
	private final String firstName;
	private final String lastName;
	private final String address;
	
}
