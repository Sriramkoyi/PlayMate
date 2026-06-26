package com.example.sport.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sport.dto.response.SportResponse;
import com.example.sport.entity.Sport;
import com.example.sport.repository.SportRepository;
import com.example.sport.service.SportService;

@Service
public class SportServiceImpl implements SportService{
	public final SportRepository sportRepository;
	public SportServiceImpl(SportRepository sportRepository){
		this.sportRepository=sportRepository;
	}
	@Override
	public List<SportResponse> getAllSports(){
		return this.sportRepository.findAll().stream().map(sport->new SportResponse(sport.getId(),sport.getName(),sport.getImageUrl())).toList();
	}
}
