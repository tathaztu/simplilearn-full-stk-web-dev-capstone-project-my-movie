package com.mymovie.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MOVIE_GENRE")
public class MovieGenre {

	@Id
	@Column(name = "GENRE_CODE")
	private String strGenreCode;

	@Column(name = "GENRE_NAME")
	private String strGenreName;

	@Column(name = "DESCRIPTION")
	private String strDescription;

	public String getStrGenreCode() {
		return strGenreCode;
	}

	public void setStrGenreCode(String strGenreCode) {
		this.strGenreCode = strGenreCode;
	}

	public String getStrGenreName() {
		return strGenreName;
	}

	public void setStrGenreName(String strGenreName) {
		this.strGenreName = strGenreName;
	}

	public String getStrDescription() {
		return strDescription;
	}

	public void setStrDescription(String strDescription) {
		this.strDescription = strDescription;
	}
}
