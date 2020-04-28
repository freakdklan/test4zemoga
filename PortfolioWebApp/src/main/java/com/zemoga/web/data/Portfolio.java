package com.zemoga.web.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "portfolio")
public class Portfolio {
	
	@Id
	@Column(name = "idportfolio")
	private Integer idportfolio;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "image_url")
	private String image_url;
	
	@Column(name = "twitter_user_name")
	private String twitter_user_name;
	
	@Column(name = "title")
	private String title;

	public Integer getIdportfolio() {
		return idportfolio;
	}

	public void setIdportfolio(Integer idportfolio) {
		this.idportfolio = idportfolio;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getTwitter_user_name() {
		return twitter_user_name;
	}

	public void setTwitter_user_name(String twitter_user_name) {
		this.twitter_user_name = twitter_user_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
