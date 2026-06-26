package com.example.sport.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sport.dto.response.SportResponse;
import com.example.sport.entity.Sport;

@Service
public interface SportService {
	List<SportResponse> getAllSports();
}
