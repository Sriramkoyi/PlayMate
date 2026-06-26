package com.example.sport.config;

import org.hibernate.property.access.internal.PropertyAccessStrategyIndexBackRefImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.sport.entity.Sport;
import com.example.sport.repository.SportRepository;

@Component
public class SportInitializer implements CommandLineRunner{
	public final SportRepository sportRepository;
	public SportInitializer(SportRepository sportRepository) {
		this.sportRepository=sportRepository;
	}
	@Override
	public void run(String... args) {
		if(sportRepository.count()>0) {
			return;
		}
		saveSport("Cricket","/images/sports/cricket.png",2,22);
		saveSport("Basketball","/images/sports/basketball.png",2,22);
		saveSport("Badminton","/images/sports/badminton.png",2,22);
		saveSport("Football","/images/sports/football.png",2,22);
		saveSport("Volleyball","/images/sports/volleyball.png",2,22);
	}
	public void saveSport(String name,String imageurl,int minplayers,int maxplayers) {
		Sport sport=new Sport();
		sport.setName(name);
		sport.setImageUrl(imageurl);
		sport.setMinPlayers(minplayers);
		sport.setMaxPlayeres(maxplayers);
		sportRepository.save(sport);
	}
	
}
