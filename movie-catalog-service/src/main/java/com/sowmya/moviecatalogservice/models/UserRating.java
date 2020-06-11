package com.sowmya.moviecatalogservice.models;

import java.util.List;

public class UserRating {

	private String user;
	private List<Rating> ratings;

	public UserRating(String user, List<Rating> ratings) {
		super();
		this.user = user;
		this.ratings = ratings;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public UserRating() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserRating [user=" + user + ", ratings=" + ratings + "]";
	}

}
