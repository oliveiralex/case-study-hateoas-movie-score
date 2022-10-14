package com.devsuperior.dsmovie.dto;

import com.devsuperior.dsmovie.projections.MovieScoresProjection;

public class MovieScoresDTO {
	
	private String title;
	private String email;
	private Double score;
	
	public MovieScoresDTO() {
	}

	public MovieScoresDTO(String title, String email, Double score) {
		this.title = title;
		this.email = email;
		this.score = score;
	}
	
	public MovieScoresDTO(MovieScoresProjection projection) {
		title = projection.getTitle();
		email = projection.getEmail();
		score = projection.getScore();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getScore_value() {
		return score;
	}

	public void setScore_value(Double score_value) {
		this.score = score_value;
	}

	@Override
	public String toString() {
		return "MovieScoresDTO [title=" + title + ", email=" + email + ", score=" + score + "]";
	}
}
