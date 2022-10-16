package com.mymovie.domain;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "MOVIE_SHOW_TIMES")
public class ShowTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SHOW_TIME_ID")
	private long longShowTimeId;

	@ManyToOne
	@JoinColumn(name = "MOVIE_ID")
	@JsonIgnore
	private Movie movie;

	@Column(name = "SHOW_TIME", columnDefinition = "TIME")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime ltShowTime;

	public ShowTime() {}

	public ShowTime(long longShowTimeId, LocalTime ltShowTime) {
		this.longShowTimeId = longShowTimeId;
		this.ltShowTime = ltShowTime;
	}

	public long getLongShowTimeId() {
		return longShowTimeId;
	}

	public void setLongShowTimeId(long longShowTimeId) {
		this.longShowTimeId = longShowTimeId;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public LocalTime getLtShowTime() {
		return ltShowTime;
	}

	public void setLtShowTime(LocalTime ltShowTime) {
		this.ltShowTime = ltShowTime;
	}
}
