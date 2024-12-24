package com.publisher.PublisherService.Service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publisher.PublisherService.Exception.PublisherNotFoundException;
import com.publisher.PublisherService.Model.Publisher;
import com.publisher.PublisherService.Repository.PublisherRepository;
import com.publisher.PublisherService.Service.PublisherService;

@Service
public class PublisherServiceImpl implements PublisherService{

	@Autowired
	private PublisherRepository publisherRepository;
	
	@Override
	public Publisher addPublisher(Publisher publisher) {
		publisher.setCreatedDate(LocalDateTime.now());
		System.out.println(publisher);
		Publisher newPublisher = publisherRepository.save(publisher);
		return newPublisher;
	}

	@Override
	public List<Publisher> getAllPublisher() {
		List<Publisher> publisherList = publisherRepository.findAll();		
		return publisherList;
	}

	@Override
	public Publisher getPublisherById(Long id) throws PublisherNotFoundException {
		Optional<Publisher> publisherOptional = publisherRepository.findById(id);
		if(publisherOptional.isEmpty()) {
			throw new PublisherNotFoundException();
		}
		Publisher publisher = publisherOptional.get();
		
		return publisher;
	}

	@Override
	public Publisher updatePublisher(Long id, Publisher publisher) throws PublisherNotFoundException {
		Optional<Publisher> publisherOptional = publisherRepository.findById(id);
		if(publisherOptional.isEmpty()) {
			throw new PublisherNotFoundException();
		}
		Publisher existingPublisher = publisherOptional.get();
    	if(existingPublisher != null) {
    		existingPublisher.setFirstName(publisher.getFirstName());
    		existingPublisher.setLastName(publisher.getLastName());    		
    		return publisherRepository.save(existingPublisher);
    	}
    	return null;
	}

	@Override
	public boolean deletePublisher(Long id) {
		if(publisherRepository.existsById(id)) {    		
    		publisherRepository.deleteById(id);
    		return true;
    	}
    	return false;
	}

}
