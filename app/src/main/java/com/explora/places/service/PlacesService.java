package com.explora.places.service;

import com.explora.places.externalservice.models.Place;

import java.util.List;

/**
 * Created by sumit.khandelwal1 on 07-Jan-18.
 */

public interface PlacesService {
    List<Place> getPlaces(double latitude, double longitude);

}
