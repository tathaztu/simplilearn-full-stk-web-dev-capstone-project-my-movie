package com.mymovie.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "USER_CONFIG")
public class User {

	@Id
	@Column(name = "USER_ID")
	private String strUserId;

	@Column(name = "FIRST_NAME")
	private String strFirstName;

	@Column(name = "MIDDLE_NAME")
	private String strMiddleName;

	@Column(name = "LAST_NAME")
	private String strLastName;

	@Column(name = "EMAIL")
	private String strEMail;

	@Column(name = "PASSWORD")
	private String strPassword;

	@Column(name = "IS_ADMIN")
	private boolean bAdminUser;

	User(){}

	public User(String strUserId, String strFirstName, String strMiddleName, String strLastName, String strEMail, String strPassword) {
		super();
		this.strUserId = strUserId;
		this.strFirstName = strFirstName;
		this.strMiddleName = strMiddleName;
		this.strLastName = strLastName;
		this.strEMail = strEMail;
		this.strPassword = strPassword;
	}

	public String getStrUserId() {
		return strUserId;
	}

	public void setStrUserId(String strUserId) {
		this.strUserId = strUserId;
	}

	public String getStrFirstName() {
		return strFirstName;
	}

	public void setStrFirstName(String strFirstName) {
		this.strFirstName = strFirstName;
	}

	public String getStrMiddleName() {
		return strMiddleName;
	}

	public void setStrMiddleName(String strMiddleName) {
		this.strMiddleName = strMiddleName;
	}

	public String getStrLastName() {
		return strLastName;
	}

	public void setStrLastName(String strLastName) {
		this.strLastName = strLastName;
	}

	public String getStrEMail() {
		return strEMail;
	}

	public void setStrEMail(String strEMail) {
		this.strEMail = strEMail;
	}

	@JsonIgnore
	public String getStrPassword() {
		return strPassword;
	}

	@JsonProperty
	public void setStrPassword(String strPassword) {
		this.strPassword = strPassword;
	}

	public boolean isbAdminUser() {
		return bAdminUser;
	}

	public void setbAdminUser(boolean bAdminUser) {
		this.bAdminUser = bAdminUser;
	}

}
