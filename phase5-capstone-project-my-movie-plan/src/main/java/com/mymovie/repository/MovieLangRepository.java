package com.mymovie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymovie.domain.MovieLang;

public interface MovieLangRepository extends JpaRepository<MovieLang, String>{

}
