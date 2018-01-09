package com.explora.places.service.impl;

import java.io.IOException;
import java.util.List;

import com.explora.places.externalservice.models.Direction;
import com.explora.places.externalservice.models.Row;
import com.explora.places.service.TimeParser;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TimeParserImpl implements TimeParser {

	@Override
	public List<Row> parse(String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Direction readValue = mapper.readValue(json, Direction.class);
			return readValue.getRows();
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
