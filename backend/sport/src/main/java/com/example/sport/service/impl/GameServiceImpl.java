package com.example.sport.service.impl;


import com.example.sport.dto.request.CreateGameRequest;
import com.example.sport.dto.request.UpdateGameRequest;
import com.example.sport.dto.response.GameResponse;
import com.example.sport.dto.response.SportResponse;
import com.example.sport.entity.Game;
import com.example.sport.entity.GameParticipant;
import com.example.sport.entity.GameStatus;
import com.example.sport.entity.Sport;
import com.example.sport.entity.User;
import com.example.sport.repository.GameParticipantRepository;
import com.example.sport.repository.GameRepository;
import com.example.sport.repository.SportRepository;
import com.example.sport.repository.UserRepository;
import com.example.sport.service.GameService;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextChangedEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService{
	private final SportRepository sportRepository;
	private final UserRepository userRepository;
	private final GameRepository gameRepository;
	private final GameParticipantRepository gameParticipantRepository;
	public GameServiceImpl(SportRepository sportRepository,UserRepository userRepository,GameRepository gameRepository,GameParticipantRepository gameParticipantRepository) {
		this.sportRepository=sportRepository;
		this.userRepository=userRepository;
		this.gameRepository=gameRepository;
		this.gameParticipantRepository=gameParticipantRepository;
	}
	@Override
	public GameResponse createGame(CreateGameRequest request) {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		User host=userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
		Sport sport=sportRepository.findById(request.getSportId()).orElseThrow(()->new RuntimeException("Sport not found"));
		Game game=new Game();
		game.setTitle(request.getTitle());
		game.setDescription(request.getDescription());
		game.setVenue(request.getVenueName());
		game.setAddress(request.getAddress());
		game.setLatitude(request.getLatitude());
		game.setLongitude(request.getLongitude());
		game.setGameDate(request.getGameDate());
		game.setStartTime(request.getStartTime());
		game.setEndtime(request.getEndTime());
		game.setEntryFee(request.getEntryFee());
		game.setMaxPlayers(request.getMaxPlayers());
		game.setCurrentPlayers(1);
		game.setStatus(GameStatus.OPEN);
		game.setHost(host);
		game.setSport(sport);
		Game savedGame=gameRepository.save(game);
		GameResponse response=new GameResponse();
		response.setId(savedGame.getId());
        response.setTitle(savedGame.getTitle());
        response.setDescription(savedGame.getDescription());

        response.setSportName(savedGame.getSport().getName());

        response.setHostName(savedGame.getHost().getName());

        response.setVenueName(savedGame.getVenue());

        response.setAddress(savedGame.getAddress());

        response.setGameDate(savedGame.getGameDate());

        response.setStartTime(savedGame.getStartTime());

        response.setEndTime(savedGame.getEndtime());

        response.setEntryFee(savedGame.getEntryFee());

        response.setCurrentPlayers(savedGame.getCurrentPlayers());

        response.setMaxPlayers(savedGame.getMaxPlayers());

        response.setStatus(savedGame.getStatus().name());

        return response;
	}
	@Override
	public List<GameResponse> getAllGames(){
		return gameRepository.findAll().stream().map(this::mapToResponse).toList();
	}
	private GameResponse mapToResponse(Game game) {

	    GameResponse response = new GameResponse();

	    response.setId(game.getId());

	    response.setTitle(game.getTitle());

	    response.setDescription(game.getDescription());

	    response.setSportName(
	            game.getSport().getName());

	    response.setHostName(
	            game.getHost().getName());

	    response.setVenueName(
	            game.getVenue());

	    response.setAddress(
	            game.getAddress());

	    response.setGameDate(
	            game.getGameDate());

	    response.setStartTime(
	            game.getStartTime());

	    response.setEndTime(
	            game.getEndtime());

	    response.setEntryFee(
	            game.getEntryFee());

	    response.setCurrentPlayers(
	            game.getCurrentPlayers());

	    response.setMaxPlayers(
	            game.getMaxPlayers());

	    response.setStatus(
	            game.getStatus().name());

	    return response;
	}
	@Override
	public void joinGame(Long gameId) {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		User user=userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
		Game game=gameRepository.findById(gameId).orElseThrow(()->new RuntimeException("Game not found"));
		if(game.getStatus()!=GameStatus.OPEN) {
			throw new RuntimeException("Game is not open to join");
		}
		if(game.getHost().getId().equals(user.getId())) {
			throw new RuntimeException("Host is already part of game");
		}
		boolean alreadyJoined=gameParticipantRepository.existsByGameAndUser(game, user);
		if(alreadyJoined) {
			throw new RuntimeException("You already joined this game");
		}
		if(game.getCurrentPlayers()>=game.getMaxPlayers()) {
			throw new RuntimeException("Game is full");
		}
		GameParticipant gameParticipant=new GameParticipant();
		gameParticipant.setGame(game);
		gameParticipant.setUser(user);
		gameParticipantRepository.save(gameParticipant);
		game.setCurrentPlayers(game.getCurrentPlayers()+1);
		if(game.getCurrentPlayers()==game.getMaxPlayers()) {
			game.setStatus(GameStatus.FULL);
		}
		gameRepository.save(game);
	}
	@Override
	public GameResponse getGameById(Long gameId) {
		Game game= gameRepository.findById(gameId).orElseThrow(()-> new RuntimeException("Game not found"));
		return mapToResponse(game);
	}
	@Override
	public List<GameResponse> getMyGames(){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		User host=userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
		return gameRepository.findByHost(host).stream().map(this::mapToResponse).toList();
		
	}
	@Override
	public List<GameResponse> getMyJoinedGames(){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		User user=userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
		return gameParticipantRepository.findByUser(user).stream().map(GameParticipant::getGame).map(this::mapToResponse).toList();
	}
	@Override
	public void leaveGame(Long gameId) {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		User user=userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
		Game game=gameRepository.findById(gameId).orElseThrow(()->new RuntimeException("Game not found"));
		if(game.getHost().getId().equals(user.getId())){
			throw new RuntimeException("Host cannot leave the game");
		}
		GameParticipant gameParticipant=gameParticipantRepository.findByGameAndUser(game, user).orElseThrow(()->new RuntimeException("You are not a part of this game"));
		gameParticipantRepository.delete(gameParticipant);
		game.setCurrentPlayers(game.getCurrentPlayers()-1);
		if(game.getCurrentPlayers()<game.getMaxPlayers()) {
			game.setStatus(GameStatus.OPEN);
		}
		gameRepository.save(game);
		
	}
	@Override
	public GameResponse updateGame(Long gameId,UpdateGameRequest request) {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		User user=userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
		Game game=gameRepository.findById(gameId).orElseThrow(()->new RuntimeException("Game not found"));
		if(!game.getHost().getId().equals(user.getId())) {
			throw new RuntimeException("Only host can edit");
		}
		if(request.getMaxPlayers()<game.getCurrentPlayers()) {
			throw new RuntimeException("Max players cannot be less than current players");
		}
		game.setTitle(request.getTitle());

	    game.setDescription(request.getDescription());

	    game.setVenue(request.getVenueName());

	    game.setAddress(request.getAddress());

	    game.setLatitude(request.getLatitude());

	    game.setLongitude(request.getLongitude());

	    game.setGameDate(request.getGameDate());

	    game.setStartTime(request.getStartTime());

	    game.setEndtime(request.getEndTime());

	    game.setEntryFee(request.getEntryFee());

	    game.setMaxPlayers(request.getMaxPlayers());
	    if (game.getCurrentPlayers() < game.getMaxPlayers()) {
	        game.setStatus(GameStatus.OPEN);
	    }

	    Game updatedGame = gameRepository.save(game);

	    return mapToResponse(updatedGame);
		
	}
	@Override 
	public void cancelGame(Long gameId) {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		User user=userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
		Game game=gameRepository.findById(gameId).orElseThrow(()->new RuntimeException("Game not found"));
		if(!game.getHost().getId().equals(user.getId())) {
			throw new RuntimeException("Only host can cancel");
		}
		if (game.getStatus() == GameStatus.CANCELLED) {

	        throw new RuntimeException(
	                "Game is already cancelled");
	    }

	    game.setStatus(GameStatus.CANCELLED);

	    gameRepository.save(game);
		
	}
	@Override
	public List<GameResponse> getGamesBySport(Long sportId){
		return gameRepository.findBySportIdAndStatus(sportId, GameStatus.OPEN).stream().map(this::mapToResponse).toList();
	}
	@Override
	public List<GameResponse> getNearByGames(Double latitude,Double longitude,Double distance){
		return gameRepository.findByStatus(GameStatus.OPEN).stream().filter(game->{
			double d=calculateDistance(latitude,longitude,game.getLatitude(),game.getLongitude());
			return d<=distance;
		}).map(game->{
			GameResponse response=mapToResponse(game);
			double d=calculateDistance(latitude,longitude,game.getLatitude(),game.getLongitude());
			response.setDistance(Math.round(d*100.0)/100/.0);
			return response;
		})
		.sorted((g1,g2)->Double.compare(g1.getDistance(),g2.getDistance()))
		.toList();
	}
	private double calculateDistance(

	        double lat1,
	        double lon1,

	        double lat2,
	        double lon2) {

	    final int EARTH_RADIUS = 6371;

	    double dLat = Math.toRadians(lat2 - lat1);
	    double dLon = Math.toRadians(lon2 - lon1);

	    double a =
	            Math.sin(dLat / 2) * Math.sin(dLat / 2)
	                    + Math.cos(Math.toRadians(lat1))
	                    * Math.cos(Math.toRadians(lat2))
	                    * Math.sin(dLon / 2)
	                    * Math.sin(dLon / 2);

	    double c = 2 * Math.atan2(
	            Math.sqrt(a),
	            Math.sqrt(1 - a));

	    return EARTH_RADIUS * c;
	}
}
