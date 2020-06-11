package com.sowmya.moviecatalogservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sowmya.moviecatalogservice.models.Movie;

@Service
@EnableHystrix
public class MovieInfoService {

	@Autowired	
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getMovieInfoFallBackMthd")
	public Movie getMovieInfo(String movieId)
	{
		Movie movie = restTemplate.getForObject("http://movie-info-service/movieInfo/" + movieId,
				Movie.class);
		return movie;
	}
	private Movie getMovieInfoFallBackMthd(String movieId)
	{
		return new Movie("No Movie", "", "");
	}

}
