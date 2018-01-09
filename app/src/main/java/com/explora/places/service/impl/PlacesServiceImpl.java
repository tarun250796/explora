package com.explora.places.service.impl;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.explora.places.externalservice.models.Place;
import com.explora.places.externalservice.models.Row;
import com.explora.places.service.PlacesConnectorService;
import com.explora.places.service.PlacesParser;
import com.explora.places.service.PlacesService;
import com.explora.places.service.TimeParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlacesServiceImpl extends AsyncTask<Double, Void, List<Place>> {

	@Override
	public List<Place> doInBackground(Double... latLng) {
		List<String> types = getTypes();
 		final String request="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%s,%s&radius=%s&types=%s&key=%s";
		final String PLACES_API_KEY="AIzaSyA3S0dyLtNhU_5WrottoeJm5z08DaMZHzA";	// AIzaSyATNyhxy9JbxvLSJppbvDIKzHkBXPCDAg4
		final String radius=String.valueOf(5000);
//		final String time_request="https://maps.googleapis.com/maps/api/distancematrix/json?origins=%s,%s&destinations=%s,%s&key=%s";
//		final String MAPS_API_KEY="AIzaSyATNyhxy9JbxvLSJppbvDIKzHkBXPCDAg4";
				
		PlacesConnectorService ncs= new PlacesConnectorServiceImpl();
		PlacesParser parser = new PlacesParserImpl();
		//TimeParser time_parser = new TimeParserImpl();
		List<Place> combinedList = new ArrayList<>();
		for(String type: types) {
			String response = ncs.connect(String.format(request, latLng[0], latLng[1], radius, type, PLACES_API_KEY));
			combinedList.addAll(parser.parse(response));
		}
		Collections.sort(combinedList, new RatingComparator());
		return combinedList;
	}

	@NonNull
	private List<String> getTypes() {
		List<String> types = new ArrayList<String>();
		types.add("bar");
		types.add("amusement_park");
		types.add("aquarium");
		types.add("art_gallery");
		types.add("bowling_alley");
		types.add("cafe");
		types.add("casino");
		types.add("church");
		types.add("hindu_temple");
		types.add("zoo");
		types.add("synagogue");
		types.add("shopping_mall");
		types.add("rv_park");
		types.add("restaurant");
		types.add("park");
		types.add("museum");
		types.add("movie_theater");
		types.add("night_club");
		return types;
	}

}
