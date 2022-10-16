package com.mymovie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymovie.domain.MovieGenre;

public interface MovieGenreRepository extends JpaRepository<MovieGenre, String>{
}
