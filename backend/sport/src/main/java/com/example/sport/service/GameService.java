package com.example.sport.service;

import java.util.List;


import com.example.sport.dto.request.CreateGameRequest;
import com.example.sport.dto.request.UpdateGameRequest;
import com.example.sport.dto.response.GameResponse;
import com.example.sport.entity.Game;
import com.example.sport.entity.GameParticipant;

public interface GameService {
	GameResponse createGame(CreateGameRequest request);
	List<GameResponse> getAllGames();
	void joinGame(Long gameId);
	GameResponse getGameById(Long gameId);
	List<GameResponse> getMyGames();
	List<GameResponse> getMyJoinedGames();
	void leaveGame(Long gameId);
	GameResponse updateGame(Long gameId,UpdateGameRequest request);
	void cancelGame(Long gameId);
}
