package com.example.sport.dto.response;

import java.util.function.LongToDoubleFunction;

public class SportResponse {
	private Long id;
	private String name;
	private String imageUrl;
	public SportResponse() {}
	public SportResponse(Long id, String name, String imageUrl) {
		super();
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	

}
