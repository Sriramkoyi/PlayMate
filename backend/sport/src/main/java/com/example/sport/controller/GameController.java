package com.example.sport.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.sport.dto.request.CreateGameRequest;
import com.example.sport.dto.request.UpdateGameRequest;
import com.example.sport.dto.response.GameResponse;
import com.example.sport.entity.Game;
import com.example.sport.repository.GameRepository;
import com.example.sport.service.GameService;

import java.util.List;

import org.springframework.http.HttpStatus;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/v1/games")
public class GameController {
	private final GameService gameService;
	public GameController(GameService gameService) {
		this.gameService=gameService;
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GameResponse createGame(@Valid @RequestBody CreateGameRequest request) {
		return gameService.createGame(request);
	}
	@GetMapping
	public List<GameResponse> getAllGames(@RequestParam(required = false) Long sportId){
		if(sportId==null) {
			return gameService.getAllGames();
		}
		return gameService.getGamesBySport(sportId);
	}
	@PostMapping("/{gameId}/join")
	public String joinGame(@PathVariable Long gameId) {
		gameService.joinGame(gameId);
		return "Joined Successfully";
	}
	@GetMapping("/{gameId}")
	public GameResponse getGameById(@PathVariable Long gameId) {
		return gameService.getGameById(gameId);
	}
	@GetMapping("/my-games")
	public List<GameResponse> getMygames(){
		return gameService.getMyGames();
	}
	@GetMapping("/my-joinedgames")
	public List<GameResponse> getMyJoinedGames(){
		return gameService.getMyJoinedGames();
	}
	@DeleteMapping("/{gameId}/leavegame")
	public void leaveGame(@PathVariable Long gameId) {
		gameService.leaveGame(gameId);
	}
	@PutMapping("{gameId}")
	public GameResponse editGame(@PathVariable Long gameId,@Valid @RequestBody UpdateGameRequest request) {
		return gameService.updateGame(gameId, request);
	}
	@DeleteMapping("{gameId}")
	public String deleteGame(@PathVariable Long gameId) {
		gameService.cancelGame(gameId);
		return "Game cancelled successfully";
	}
	@GetMapping("/nearby")
	public List<GameResponse> getNearByGames(@RequestParam Double latitude,@RequestParam Double longitude,@RequestParam(defaultValue = "5") Double distance){
		return gameService.getNearByGames(latitude, longitude, distance);
	}
}
