package com.explora.places.service;

import com.explora.places.externalservice.models.Place;

import java.util.List;


public interface PlacesParser {
	public List<Place> parse(String json);
}
