package com.example.sport.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="games")
public class Game extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String title;
	@Column(length = 1000)
	private String description;
	@Column(nullable = false)
	private String venue;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private Double latitude;
	@Column(nullable = false)
	private Double longitude;
	@Column(nullable = false)
	private LocalDate gameDate;
	@Column(nullable = false)
	private LocalTime startTime;
	@Column(nullable = false)
	private LocalTime endtime;
	@Column(nullable = false,precision = 10,scale = 2)
	private BigDecimal entryFee;
	@Column(nullable = false)
	private Integer maxPlayers;
	@Column(nullable = false)
	private Integer currentPlayers;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private GameStatus status;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="host_id",nullable = false)
	private User host;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sport_id",nullable = false)
	private Sport sport;
	public Game() {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndtime() {
		return endtime;
	}
	public void setEndtime(LocalTime endtime) {
		this.endtime = endtime;
	}
	public BigDecimal getEntryFee() {
		return entryFee;
	}
	public void setEntryFee(BigDecimal entryFee) {
		this.entryFee = entryFee;
	}
	public Integer getMaxPlayers() {
		return maxPlayers;
	}
	public void setMaxPlayers(Integer maxPlayers) {
		this.maxPlayers = maxPlayers;
	}
	public Integer getCurrentPlayers() {
		return currentPlayers;
	}
	public void setCurrentPlayers(Integer currentPlayers) {
		this.currentPlayers = currentPlayers;
	}
	public GameStatus getStatus() {
		return status;
	}
	public void setStatus(GameStatus status) {
		this.status = status;
	}
	public User getHost() {
		return host;
	}
	public void setHost(User host) {
		this.host = host;
	}
	public Sport getSport() {
		return sport;
	}
	public void setSport(Sport sport) {
		this.sport = sport;
	}
	public LocalDate getGameDate() {
	    return gameDate;
	}

	public void setGameDate(LocalDate gameDate) {
	    this.gameDate = gameDate;
	}
	
	
	
	
}
