package com.mymovie.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MOVIE_LANG")
public class MovieLang {

	@Id
	@Column(name = "LANG_CODE")
	private String strLangCode;

	@Column(name = "LANG_NAME")
	private String strLangName;

	@Column(name = "DESCRIPTION")
	private String strDescription;

	public String getStrLangCode() {
		return strLangCode;
	}

	public void setStrLangCode(String strLangCode) {
		this.strLangCode = strLangCode;
	}

	public String getStrLangName() {
		return strLangName;
	}

	public void setStrLangName(String strLangName) {
		this.strLangName = strLangName;
	}

	public String getStrDescription() {
		return strDescription;
	}

	public void setStrDescription(String strDescription) {
		this.strDescription = strDescription;
	}
}
