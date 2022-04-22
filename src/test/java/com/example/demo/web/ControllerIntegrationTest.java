package com.example.demo.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.demo.domain.PlayerCard;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts= {"classpath:playerCard-schema.sql","classpath:playerCard-data.sql"}, executionPhase=ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class ControllerIntegrationTest {
	
	@Autowired // gets Mockmvc obj from context
	
	private MockMvc mvc; // class that will perform requests for tests like Postman
	
	@Autowired
	private ObjectMapper mapper; // converts from Java to JSON
	
	@Test
	void testCreate() throws Exception {
		PlayerCard testPlayer = new PlayerCard(null, "Cech", 44, "GK", "Chelsea"); // create PlayerCard for request
		String testPlayerAsJSON = this.mapper.writeValueAsString(testPlayer); // create PlayerCard obj to JSON 
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testPlayerAsJSON); // simulates request being sent to server
		
		PlayerCard testCreatedPlayer = new PlayerCard(3, "Cech", 44, "GK", "Chelsea");
		String testCreatedPlayerAsJSON = this.mapper.writeValueAsString(testCreatedPlayer);
		
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedPlayerAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody); // checks if PlayerCard is created and with correct status code
	}
	
	@Test
	void getAllTest() throws Exception {
		RequestBuilder req = get("/getAll");
		
		List<PlayerCard> testPlayers = List.of(new PlayerCard(1, "Baller1", 18, "ST", "Chelsea"),new PlayerCard(2,"Baller2" ,22, "DM", "Chelsea"));
		String json = this.mapper.writeValueAsString(testPlayers);
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getByIdTest() throws Exception {
		RequestBuilder req = get("/get/1");
		String json = this.mapper.writeValueAsString(new PlayerCard(1, "Baller1", 18, "ST", "Chelsea"));
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getByPositionTest() throws Exception {
		RequestBuilder req = get("/getPlayerByPosition/ST");
		
		List<PlayerCard> testPlayers = List.of(new PlayerCard(1,"Baller1", 18, "ST", "Chelsea"));
		String json = this.mapper.writeValueAsString(testPlayers);
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getByPlayerNameTest() throws Exception {
		RequestBuilder req = get("/getPlayerByName/Baller1");
		
		List<PlayerCard> testPlayers = List.of(new PlayerCard(1,"Baller1", 18, "ST", "Chelsea"));
		String json = this.mapper.writeValueAsString(testPlayers);
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	@Test
	void testReplace() throws Exception {
		PlayerCard testPlayer = new PlayerCard(null, "Mendy", 33, "GK", "Chelsea"); 
		String testPlayerAsJSON = this.mapper.writeValueAsString(testPlayer); 
		RequestBuilder req = put("/replace/1").contentType(MediaType.APPLICATION_JSON).content(testPlayerAsJSON);
		
		PlayerCard testUpdatedPlayer = new PlayerCard(1, "Mendy", 33, "GK", "Chelsea");
		String testUpdatedPlayerAsJSON = this.mapper.writeValueAsString(testUpdatedPlayer);
		
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testUpdatedPlayerAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testRemove() throws Exception {
		this.mvc.perform(delete("/remove/1")).andExpect(status().isNoContent());
	}

}
	


