package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.PlayerCard;
import com.example.demo.repo.PlayerCardRepo;

@Service
public class PlayerCardService implements ServiceIF<PlayerCard> {
	
	private PlayerCardRepo repo;
	
	@Autowired
	public PlayerCardService(PlayerCardRepo repo) {
		super();
		this.repo=repo;
	}

	//INSERT INTO PLAYERCARD
	public PlayerCard create(PlayerCard p) {
		PlayerCard created = this.repo.save(p);
		return created;
	}

	// SELECT * FROM PLAYERCARD
	public List<PlayerCard> getAll() {
		return this.repo.findAll();
	}

	// SELECT * FROM PLAYERCARD WHERE ID = 
	public PlayerCard getSingle(Integer id) {
		Optional<PlayerCard> found = this.repo.findById(id);
		return found.get();
	}

	// UPDATE
	public PlayerCard replace(Integer id, PlayerCard newPlayer) {
		PlayerCard existing = this.repo.findById(id).get();
		existing.setPlayerName(newPlayer.getPlayerName());
		existing.setAge(newPlayer.getAge());
		existing.setPlayerPosition(newPlayer.getPlayerPosition());
		existing.setFootballClub(newPlayer.getFootballClub());
		PlayerCard updated = this.repo.save(existing);
		return updated;
	}

	// DELETE FROM PLAYERCARD WHERE ID =
	public void remove(Integer id) {
		this.repo.deleteById(id);
		
	}
	
	// EXTRA FUNCTIONS 'FIND BY CLUB will be in future version of app'
	
	// SELECT * FROM PLAYERCARD WHERE POSITION =
	public List<PlayerCard> getPlayerByPosition(String playerPosition) {
		List<PlayerCard> found = this.repo.findByPlayerPositionIgnoreCase(playerPosition);
		return found;
	}
	
	public List<PlayerCard> getPlayerByClub(String footballClub) {
		List<PlayerCard> found = this.repo.findByFootballClubIgnoreCase(footballClub);
		return found;
	}

}
