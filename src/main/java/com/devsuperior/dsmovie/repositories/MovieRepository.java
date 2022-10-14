package com.devsuperior.dsmovie.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmovie.entities.Movie;
import com.devsuperior.dsmovie.projections.MovieScoresProjection;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	@Query(nativeQuery = true, value = "SELECT m.title, u.email, s.score_value AS score "
			+ "FROM TB_SCORE AS s "
			+ "INNER JOIN TB_MOVIE AS m ON m.id = s.movie_id "
			+ "INNER JOIN TB_USER AS u ON u.id = s.user_id "
			+ "WHERE m.id = :movie_id")
	List<MovieScoresProjection> search1(Long movie_id);

}