package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.PlayerCard;

@Repository
public interface PlayerCardRepo extends JpaRepository<PlayerCard, Integer> {
	// Automatically generates CRUD functions 
	List<PlayerCard> findByPlayerPositionIgnoreCase(String playerPosition);
	
	List<PlayerCard> findByPlayerNameIgnoreCase(String playerName);

}
