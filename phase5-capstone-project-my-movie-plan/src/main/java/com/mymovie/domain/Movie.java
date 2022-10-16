package com.mymovie.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "MOVIES")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MOVIE_ID")
	private long longMovieId;

	@Column(name = "GENRE_CODE")
	private String strGenreCode;

	@Column(name = "LANG_CODE")
	private String strLangCode;

	@Column(name = "MOVIE_NAME")
	private String strMovieName;

	@Column(name = "DESCRIPTION")
	private String strDesc;

	@Column(name = "STARRING")
	private String strStarring;

	@Column(name = "DIRECTED_BY")
	private String strDirectedBy;

	@Column(name = "MOVIE_PIC_PATH")
    private String strMoviePicPath;

	@Column(name = "TICKET_PRICE")
	private float flTicketPrice;

	@Column(name = "IS_ACTIVE")
	private boolean bActive;

	@OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ShowTime> listShowTimes = new ArrayList<>();

	@Transient
	private MultipartFile file;

	@JsonIgnore
	public MultipartFile getFile() {
		return file;
	}

	@JsonProperty
	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public long getLongMovieId() {
		return longMovieId;
	}

	public void setLongMovieId(long longMovieId) {
		this.longMovieId = longMovieId;
	}

	public String getStrGenreCode() {
		return strGenreCode;
	}

	public void setStrGenreCode(String strGenreCode) {
		this.strGenreCode = strGenreCode;
	}

	public String getStrLangCode() {
		return strLangCode;
	}

	public void setStrLangCode(String strLangCode) {
		this.strLangCode = strLangCode;
	}

	public String getStrMovieName() {
		return strMovieName;
	}

	public void setStrMovieName(String strMovieName) {
		this.strMovieName = strMovieName;
	}

	public String getStrDesc() {
		return strDesc;
	}

	public void setStrDesc(String strDesc) {
		this.strDesc = strDesc;
	}

	public String getStrStarring() {
		return strStarring;
	}

	public void setStrStarring(String strStarring) {
		this.strStarring = strStarring;
	}

	public String getStrDirectedBy() {
		return strDirectedBy;
	}

	public void setStrDirectedBy(String strDirectedBy) {
		this.strDirectedBy = strDirectedBy;
	}

	public String getStrMoviePicPath() {
		return strMoviePicPath;
	}

	public void setStrMoviePicPath(String strMoviePicPath) {
		this.strMoviePicPath = strMoviePicPath;
	}

	public float getFlTicketPrice() {
		return flTicketPrice;
	}

	public void setFlTicketPrice(float flTicketPrice) {
		this.flTicketPrice = flTicketPrice;
	}

	public boolean isbActive() {
		return bActive;
	}

	public void setbActive(boolean bActive) {
		this.bActive = bActive;
	}

	public List<ShowTime> getListShowTimes() {
		return listShowTimes;
	}

	public void setListShowTimes(List<ShowTime> listShowTimes) {
		this.listShowTimes = listShowTimes;

		this.listShowTimes.stream().forEach(e -> e.setMovie(this));
	}
}
