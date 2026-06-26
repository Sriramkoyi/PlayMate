package com.example.sport.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sport.dto.response.SportResponse;
import com.example.sport.service.SportService;

@RestController
@RequestMapping("/api/v1/sports")
public class SportController {
	private final SportService sportService;
	public SportController(SportService sportService) {
		this.sportService=sportService;
	}
	@GetMapping
	public List<SportResponse> getAllSports(){
		return sportService.getAllSports();
	}
}
