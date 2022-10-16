package com.mymovie.service.movie;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymovie.domain.Movie;
import com.mymovie.domain.MovieGenre;
import com.mymovie.domain.MovieLang;
import com.mymovie.repository.MovieGenreRepository;
import com.mymovie.repository.MovieLangRepository;
import com.mymovie.repository.MovieRepository;
import com.mymovie.repository.specifications.MovieSpecifications;

@Service(value = "Movie Service")
public class MovieServiceImpl implements MovieServiceIF {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private MovieLangRepository movieLangRepository;

	@Autowired
	private MovieGenreRepository movieGenreRepository;


	@Override
	public Movie getMovieById(long id) {
		Optional<Movie> optMovie = movieRepository.findById(id);
		return optMovie.isPresent()? optMovie.get() : null;
	}

	@Override
	public Movie saveMovie(Movie movie) {
		movieRepository.save(movie);
		return movie;
	}

	@Override
	public void deleteMovie(long id) {
		movieRepository.deleteById(id);
	}

	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public List<Movie> getMoviesByKeyword(String strKeyword) {
		return
			StringUtils.isBlank(strKeyword) ?
				movieRepository.findAll() :
				movieRepository.findAll(MovieSpecifications.getSpecification(strKeyword));
	}

	@Override
	public List<MovieGenre> getMovieGenres() {
		return movieGenreRepository.findAll();
	}

	@Override
	public List<MovieLang> getMovieLanguages() {
		return movieLangRepository.findAll();
	}
}
