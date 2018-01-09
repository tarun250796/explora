package com.explora.places.service.impl;

import com.explora.places.externalservice.models.Place;

import java.util.Comparator;

public class RatingComparator implements Comparator<Place> {

	public int compare(Place o1, Place o2) {
		if(o1.getRating() == o2.getRating()) {
			return 0;
		} else if (o1.getRating() > o2.getRating()) {
			return - 1;
		} else {
			return 1;
		}
	}
}
