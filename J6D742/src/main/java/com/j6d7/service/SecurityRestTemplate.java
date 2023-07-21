package com.j6d7.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class SecurityRestTemplate {
	RestTemplate client = new RestTemplate();

	public JsonNode get(String url){
		return this.request(url, HttpMethod.GET, null);
	}
	
	public JsonNode post(String url, Object data){
		return this.request(url, HttpMethod.POST, data);
	}
	
	public JsonNode put(String url, Object data){
		return this.request(url, HttpMethod.PUT, data);
	}
	
	public JsonNode delete(String url){
		return this.request(url, HttpMethod.DELETE, null);
	}
	
	private JsonNode request(String url, HttpMethod method, Object data) {
		//1. Headers
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic dXNlcjM6MTIz");
		//2. Data
		HttpEntity<Object> entity = new HttpEntity<>(data, headers);
		//3. Request & Response
		ResponseEntity<JsonNode> response = client.exchange(url, method, entity, JsonNode.class);
		return response.getBody();
	}
}
