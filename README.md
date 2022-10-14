## MovieScores Query (steps)

1. Implementing MovieScoresProjection

```java
package com.devsuperior.dsmovie.projections;

public interface MovieScoresProjection {
	
	String getTitle();
	String getEmail();
	Double getScore();
}
```



2. Add a query in MovieRepository
```java
@Query(nativeQuery = true, value = "SELECT m.title, u.email, s.score_value AS score "
			+ "FROM TB_SCORE AS s "
			+ "INNER JOIN TB_MOVIE AS m ON m.id = s.movie_id "
			+ "INNER JOIN TB_USER AS u ON u.id = s.user_id "
			+ "WHERE m.id = :movie_id")
	List<MovieScoresProjection> search1(Long movie_id);
```

3. Create a MovieScoresDTO
```java
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
```

4. Change findById in MovieService

```java
@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		Movie result = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		
		MovieDTO dto = new MovieDTO(result).add(linkTo(methodOn(MovieController.class).findAll(null)).withRel("Movies List"));
		
		
		List<MovieScoresProjection> list = repository.search1(id);
		List<MovieScoresDTO> result1 = list.stream().map(x -> new MovieScoresDTO(x)).collect(Collectors.toList());
		
		for (MovieScoresDTO obj: result1) {
			dto.getMovieScores().add(obj);
		}
		
		//return new MovieDTO(result).add(linkTo(methodOn(MovieController.class).findAll(null)).withRel("Movies List"));
		return dto;
	}
```



