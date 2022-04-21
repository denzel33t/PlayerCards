package com.example.demo.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.demo.domain.PlayerCard;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
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
		
		PlayerCard testCreatedPlayer = new PlayerCard(1, "Cech", 44, "GK", "Chelsea");
		String testCreatedPlayerAsJSON = this.mapper.writeValueAsString(testCreatedPlayer);
		
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedPlayerAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody); // checks if PlayerCard is created and with correct status code
	}

}
	


