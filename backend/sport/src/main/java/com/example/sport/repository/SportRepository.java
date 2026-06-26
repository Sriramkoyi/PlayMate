package com.example.sport.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sport.entity.Sport;

public interface SportRepository extends JpaRepository<Sport, Long>{
	Optional<Sport> findByName(String name);
	boolean existsByName(String name);

}
