package com.sowmya.moviecatalogservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sowmya.moviecatalogservice.models.CatalogItem;
import com.sowmya.moviecatalogservice.models.Movie;
import com.sowmya.moviecatalogservice.models.UserRating;
import com.sowmya.moviecatalogservice.service.MovieInfoService;
import com.sowmya.moviecatalogservice.service.UserRatingService;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	UserRatingService userRatingService;

	@Autowired
	MovieInfoService movieInfoService;

	@Autowired
	private WebClient.Builder webClientBuilder;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		UserRating userRating = userRatingService.getUserRating(userId);

		return userRating.getRatings().stream().map(rating -> {
			Movie movie = movieInfoService.getMovieInfo(rating.getMovieId());

			return new CatalogItem(movie.getMovieId(), movie.getName(), rating.getRating());
		}).collect(Collectors.toList());
	}
}



// List<Rating> ratings = Arrays.asList(new Rating("123", 4), new Rating("567",
// 5));

// return Collections.singletonList(new CatalogItem("XXX", "desc Test", 4));

// Movie movie =
// webClientBuilder.build().get().uri("http://localhost:8082/movieInfo/" +
// rating.getMovieId())
// .retrieve().bodyToMono(Movie.class).block();