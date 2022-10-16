package com.mymovie.service.movie;

import java.util.List;

import com.mymovie.domain.Movie;
import com.mymovie.domain.MovieGenre;
import com.mymovie.domain.MovieLang;

public interface MovieServiceIF {

	public Movie getMovieById(long id);

	public Movie saveMovie(Movie movie);

	public void deleteMovie(long id);

	public List<Movie> getAllMovies();

	public List<Movie> getMoviesByKeyword(String strKeyword);

	public List<MovieGenre> getMovieGenres();

	public List<MovieLang> getMovieLanguages();
}
