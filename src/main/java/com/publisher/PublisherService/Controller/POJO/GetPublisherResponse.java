package com.publisher.PublisherService.Controller.POJO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPublisherResponse {
	private List<PublisherResponse> publisherResponse;

}
