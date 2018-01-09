package com.explora.places.service.impl;

import com.explora.places.service.PlacesConnectorService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PlacesConnectorServiceImpl implements PlacesConnectorService {

	public String connect(String url) {
		HttpURLConnection httpConnection = null;
		BufferedReader br = null;
		try {
			// Create HTTP Connection to call API URL
			httpConnection = (HttpURLConnection) ((new URL(url).openConnection()));
			httpConnection.setDoOutput(true);
			httpConnection.setRequestProperty("Content-Type", "application/json");
			httpConnection.setRequestProperty("Accept", "application/json");
			httpConnection.setRequestMethod("GET");
			httpConnection.connect();
			if(httpConnection.getResponseCode() != 200) {
				throw new RuntimeException("Error response from GOOGLE API: " + httpConnection.getResponseCode());
			}
			br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
			String line = "";
			// Create response string
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (IOException e) {
			throw new RuntimeException("Error in connecting to GOOGLE API : " + e.getMessage()); 
		} finally {
			// Close HTTP Connection
			if(httpConnection != null) {
				httpConnection.disconnect();
			}
			// Close BufferedReader
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					throw new RuntimeException("Error in closing buffered reader: " + e.getMessage());
				}
			}
		}
	}
} 