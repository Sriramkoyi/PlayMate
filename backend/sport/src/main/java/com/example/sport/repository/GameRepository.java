package com.example.sport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sport.dto.response.GameResponse;
import com.example.sport.entity.Game;
import com.example.sport.entity.GameParticipant;
import com.example.sport.entity.User;


public interface GameRepository extends JpaRepository<Game, Long>{
	List<Game> findByHost(User host);
	
}