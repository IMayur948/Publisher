package com.publisher.PublisherService.Controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.publisher.PublisherService.Controller.POJO.GetPublisherResponse;
import com.publisher.PublisherService.Controller.POJO.PublisherRequest;
import com.publisher.PublisherService.Controller.POJO.PublisherResponse;
import com.publisher.PublisherService.Exception.PublisherNotFoundException;
import com.publisher.PublisherService.Model.Publisher;
import com.publisher.PublisherService.Service.PublisherService;

@RestController
@RequestMapping("/api")
public class PublisherController {
	
	@Autowired
	private PublisherService publisherService;
	

	@GetMapping
	public ResponseEntity<GetPublisherResponse> getAllPublisher(){
		List<Publisher> publisherList = publisherService.getAllPublisher();
		List<PublisherResponse> publisherResponseList = publisherList.stream()
				.map(publisher -> new PublisherResponse(publisher.getId(), publisher.getFirstName(), publisher.getLastName(), publisher.getAddress()))
				.collect(Collectors.toList());
		return new ResponseEntity<>( new GetPublisherResponse(publisherResponseList), HttpStatus.OK);

	}
	

	@GetMapping("/{id}")
	public ResponseEntity<GetPublisherResponse> getPublisherById(@PathVariable Long id) throws PublisherNotFoundException{
		Publisher publisher= publisherService.getPublisherById(id);
		PublisherResponse publisherResponse =  new PublisherResponse(publisher.getId(), publisher.getFirstName(), publisher.getLastName(), publisher.getAddress());
		List<PublisherResponse> publisherResponseList = Collections.singletonList(publisherResponse);
		
		return new ResponseEntity<>( new GetPublisherResponse(publisherResponseList), HttpStatus.OK);

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<GetPublisherResponse> updatePublisher(@PathVariable Long id, @RequestBody PublisherRequest publisherRequest) throws PublisherNotFoundException{
		Publisher publisher = new Publisher(null, publisherRequest.getFirstName(), publisherRequest.getLastName(), publisherRequest.getAddress());
		Publisher newPublisher = publisherService.updatePublisher(id, publisher);
		
		PublisherResponse publisherResponse = new PublisherResponse(newPublisher.getId(), newPublisher.getFirstName(), newPublisher.getLastName(), newPublisher.getAddress());
		List<PublisherResponse> publisherResponseList = Collections.singletonList(publisherResponse);
		
		return new ResponseEntity<>( new GetPublisherResponse(publisherResponseList), HttpStatus.OK);

	}
	
	@PostMapping
	public ResponseEntity<PublisherResponse> addPublisher(@RequestBody PublisherRequest publisherRequest){
		Publisher publisher = new Publisher(null, publisherRequest.getFirstName(),publisherRequest.getLastName(), publisherRequest.getAddress());
		Publisher newPublisher = publisherService.addPublisher(publisher);
		
		PublisherResponse publisherResponse = new PublisherResponse(newPublisher.getId(), newPublisher.getFirstName(), newPublisher.getLastName(), newPublisher.getAddress());
		return new ResponseEntity<>( publisherResponse, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePublisher(@PathVariable Long id){
		boolean isDeleted = publisherService.deletePublisher(id);
		if(isDeleted) {
			return new ResponseEntity<>("Publisher with id " + id + " deleted" , HttpStatus.OK);
		}
		return new ResponseEntity<>("Publisher not found", HttpStatus.NOT_FOUND);
	}
	
}
