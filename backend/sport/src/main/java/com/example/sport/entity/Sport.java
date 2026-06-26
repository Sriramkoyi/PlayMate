package com.example.sport.entity;

import org.hibernate.annotations.Collate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="sports")
public class Sport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false,unique = true)
	private String name;
	@Column(nullable = false)
	private String imageUrl;
	@Column(nullable = false)
	private Integer minPlayers;
	@Column(nullable = false)
	private Integer maxPlayeres;
	public Sport() {}
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
	public Integer getMinPlayers() {
		return minPlayers;
	}
	public void setMinPlayers(Integer minPlayers) {
		this.minPlayers = minPlayers;
	}
	public Integer getMaxPlayeres() {
		return maxPlayeres;
	}
	public void setMaxPlayeres(Integer maxPlayeres) {
		this.maxPlayeres = maxPlayeres;
	}
	
}
