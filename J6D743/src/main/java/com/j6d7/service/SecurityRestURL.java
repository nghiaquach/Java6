package com.j6d7.service;

import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SecurityRestURL {
	
	static ObjectMapper mapper = new ObjectMapper();
	
	static public JsonNode get(String url){
		return request(url, "GET", null);
	}
	
	static public JsonNode post(String url, Object data){
		return request(url, "POST", data);
	}
	
	static public JsonNode put(String url, Object data){
		return request(url, "PUT", data);
	}
	
	static public JsonNode delete(String url){
		return request(url, "DELETE", null);
	}
	
	static private JsonNode request(String url, String method, Object data) {
		HttpURLConnection conn = null;
		try {
			// 1. REQUEST
			conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestMethod(method);
			conn.setRequestProperty("Authorization", "Basic dXNlcjM6MTIz");
			
			// 2. DATA (POST & PUT only)
			if(data != null) {
				conn.setDoOutput(true);
			    mapper.writeValue(conn.getOutputStream(), data);
			}
			
			// 3. Processing Response
			int responseCode = conn.getResponseCode();
			if (responseCode == 200) {
			    return mapper.readTree(conn.getInputStream());
			}
			return null;
		} 
		catch (Exception e) {
			throw new RuntimeException(e);
		} 
		finally {
			conn.disconnect();
		}
	}
}
