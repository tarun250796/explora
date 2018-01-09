package com.explora.places.service;

import com.explora.places.externalservice.models.Row;

import java.util.List;


public interface TimeParser {
	public List<Row> parse(String json);
}
