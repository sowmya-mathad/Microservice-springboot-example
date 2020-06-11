package com.sowmya.moviecatalogservice.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sowmya.moviecatalogservice.models.Rating;
import com.sowmya.moviecatalogservice.models.UserRating;

@Service
@EnableHystrix
public class UserRatingService {

	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getUserRatingFallBackMthd")
	public UserRating getUserRating(String userId) {
		UserRating userRating = restTemplate.getForObject("http://rating-data-service/ratingsdata/users/userId",
				UserRating.class);

		return userRating;
	}

	private UserRating getUserRatingFallBackMthd(String userId) {
		List<Rating> ratings = Arrays.asList(new Rating("", 0));
		return new UserRating("", ratings);

	}
}
