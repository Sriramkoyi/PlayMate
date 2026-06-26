package com.example.sport.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sport.dto.response.GameResponse;
import com.example.sport.entity.Game;
import com.example.sport.entity.GameParticipant;
import com.example.sport.entity.User;

public interface GameParticipantRepository extends JpaRepository<GameParticipant, Long>{
	boolean existsByGameAndUser(Game game,User user);
	long countByGame(Game game);
	List<GameParticipant> findByUser(User user);
	Optional<GameParticipant> findByGameAndUser(Game game,User user);
	

}
