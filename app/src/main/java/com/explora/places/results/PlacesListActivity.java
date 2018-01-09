package com.explora.places.results;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.explora.places.R;
import com.explora.places.externalservice.models.Place;
import com.explora.places.service.PlacesConnectorService;
import com.explora.places.service.PlacesParser;
import com.explora.places.service.impl.PlacesConnectorServiceImpl;
import com.explora.places.service.impl.PlacesParserImpl;
import com.explora.places.service.impl.RatingComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlacesListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_list);

        // setSupportActionBar(toolbar);
        new PlacesServiceImpl().execute(getIntent().getExtras().getDouble("latitude"), getIntent().getExtras().getDouble("longitude"));
    }

    private void done(List<Place> places){
        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new RecyclerAdapter(this, places));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    class PlacesServiceImpl extends AsyncTask<Double, Void, List<Place>> {

        @Override
        public List<Place> doInBackground(Double... latLng) {
            List<String> types = getTypes();
            final String request="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%s,%s&radius=%s&types=%s&key=%s";
            final String PLACES_API_KEY="AIzaSyDwgilCrLtd2fq5LlYFsFdhdIwmmxj0QBo";	// AIzaSyATNyhxy9JbxvLSJppbvDIKzHkBXPCDAg4
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

        @Override
        protected void onPostExecute(List<Place> result) {
            done(result);
        }
    }
}