package com.explora.places.service.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.explora.places.externalservice.models.Place;
import com.explora.places.externalservice.models.Places;
import com.explora.places.service.PlacesParser;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class PlacesParserImpl implements PlacesParser {

	@Override
	public List<Place> parse(String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Places readValue = mapper.readValue(json, Places.class);
			return readValue.getResults();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
