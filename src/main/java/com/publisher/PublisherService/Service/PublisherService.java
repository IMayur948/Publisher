package com.publisher.PublisherService.Service;

import java.util.List;

import com.publisher.PublisherService.Exception.PublisherNotFoundException;
import com.publisher.PublisherService.Model.Publisher;

public interface PublisherService {
	
	Publisher addPublisher(Publisher publisher);
	List<Publisher> getAllPublisher();
	Publisher getPublisherById(Long id) throws PublisherNotFoundException;
	Publisher updatePublisher(Long id, Publisher publisher) throws PublisherNotFoundException;
	boolean deletePublisher(Long id);
}
