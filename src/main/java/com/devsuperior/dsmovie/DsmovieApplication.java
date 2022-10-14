package com.devsuperior.dsmovie;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.dsmovie.dto.MovieScoresDTO;
import com.devsuperior.dsmovie.projections.MovieScoresProjection;
import com.devsuperior.dsmovie.repositories.MovieRepository;

@SpringBootApplication
public class DsmovieApplication implements CommandLineRunner {

	@Autowired
	private MovieRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(DsmovieApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<MovieScoresProjection> list = repository.search1(2L);
		List<MovieScoresDTO> result1 = list.stream().map(x -> new MovieScoresDTO(x)).collect(Collectors.toList());
		
		System.out.println("RESULTADO SCORE MOVIES");
		for (MovieScoresDTO obj : result1) {
			System.out.println(obj);
		}
		System.out.println("\n\n");
	}

}
