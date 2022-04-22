package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // lets spring know it's a table in a database

public class PlayerCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private String playerName;
	
	@Column(nullable=false)
	private Integer Age;
	
	@Column(nullable=false)
	private String playerPosition;
	
	@Column(nullable=true) // some players may be free agents, hence this column being nullable
	private String footballClub;

	public PlayerCard(Integer id, String playerName, Integer age, String playerPosition, String footballClub) {
		this.id = id;
		this.playerName = playerName;
		Age = age;
		this.playerPosition = playerPosition;
		this.footballClub = footballClub;
	}

	public PlayerCard() { 
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Integer getAge() {
		return Age;
	}

	public void setAge(Integer age) {
		Age = age;
	}

	public String getPlayerPosition() {
		return playerPosition;
	}

	public void setPlayerPosition(String playerPosition) {
		this.playerPosition = playerPosition;
	}

	public String getFootballClub() {
		return footballClub;
	}

	public void setFootballClub(String footballClub) {
		this.footballClub = footballClub;
	}

	@Override
	public String toString() {
		return "BallerCard [id=" + id + ", playerName=" + playerName + ", Age=" + Age + ", playerPosition="
				+ playerPosition + ", footballClub=" + footballClub + "]";
	}
	
	
	
	
	
	
	

}
