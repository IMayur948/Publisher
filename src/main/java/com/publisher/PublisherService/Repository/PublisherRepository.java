package com.publisher.PublisherService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.publisher.PublisherService.Model.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long>{

}
