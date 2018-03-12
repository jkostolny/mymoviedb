package com.example.mmdb.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

	@Autowired
	private final MovieService movieService;

	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@GetMapping("/movies")
	public List<Movie> listMovies(@RequestParam(required = false, defaultValue = "0") Integer page,
								  @RequestParam(required = false, defaultValue = "10") Integer size) {
		return movieService.listInitialMovies(PageRequest.of(page, size)).getContent();
	}

	@GetMapping("/search/{searchTerm}")
	public List<Movie> search(@PathVariable String searchTerm,
							  @RequestParam(required = false, defaultValue = "0") Integer page,
							  @RequestParam(required = false, defaultValue = "10") Integer size) {
		return movieService.fullTextSearch(searchTerm, PageRequest.of(page, size)).getContent();
	}


}
