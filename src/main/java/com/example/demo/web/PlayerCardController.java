package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerCardController {

//Adding Response entities represent HTTP responses including status code(s), headers and body
	
	private PlayerCardService service;
	
	@Autowired // notifies spring to retrieve the DogService from the context - dependency injection
	public PlayerCardController(PlayerCardService service) {
		super();
		this.service = service;
	}
	
//Create
	@PostMapping("/create") //201 - created
	public ResponseEntity<PlayerCard> createPlayer(@RequestBody PlayerCard p) {
		PlayerCard created = this.service.create(p);
		ResponseEntity<PlayerCard> response = new ResponseEntity<PlayerCard>(created, HttpStatus.CREATED);
		return response;
	}

//Read All

	@GetMapping("/getAll") // 200 - OK
	public ResponseEntity<List<PlayerCard>> getAllPlayers() {
		return ResponseEntity.ok(this.service.getAll());
	}

//Read single item

	@GetMapping("/get/{id}") // 200 - OK
	public PlayerCard getPlayer(@PathVariable Integer id) {
		return this.service.getSingle(id);
	}

//Update 
	@PutMapping("/replace/{id}") // 202 - Accepted
	public ResponseEntity<PlayerCard> replace(@PathVariable Integer id, @RequestBody PlayerCard newPlayer) {
		Dog body = this.service.replace(id, newDog);
		ResponseEntity<PlayerCard> response = new ResponseEntity<PlayerCard>(body, HttpStatus.ACCEPTED);
		return response;
	}

//Delete
	@DeleteMapping("/remove/{id}") // 204 - No Content
	public ResponseEntity<?> deletePlayer(@PathVariable Integer id) {
		this.service.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
//Read by position
	@GetMapping("/getByBreed/{playerPosition}") // 200 -OK
	public ResponseEntity<List<PlayerCard>> getPlayersByPosition(@PathVariable String playerPosition){
		List<PlayerCard> found = this.service.getPlayersByPosition(playerPosition);
		return ResponseEntity.ok(found);
	}
	
//Read by club
	@GetMapping("/getByAge/{footballClub}") // 200 - OK
	public ResponseEntity<List<PlayerCard>> getPlayerByClub(@PathVariable String footballClub){
		List<PlayerCard> found = this.service.getPlayersByClub(footballClub);
		return ResponseEntity.ok(found);
	}
	
}
