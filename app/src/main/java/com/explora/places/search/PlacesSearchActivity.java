package com.explora.places.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.explora.places.R;
import com.explora.places.main.PlacesActivity;

public class PlacesSearchActivity extends AppCompatActivity {
    private DelayAutoCompleteTextView geo_autocomplete;
    private ImageView geo_autocomplete_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_search);
        geo_autocomplete_clear = (ImageView) findViewById(R.id.geo_autocomplete_clear);
        geo_autocomplete = (DelayAutoCompleteTextView) findViewById(R.id.geo_autocomplete);
        geo_autocomplete.setThreshold(5);
        geo_autocomplete.setAdapter(new GeoAutoCompleteAdapter(this)); // 'this' is Activity instance
        geo_autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                GeoSearchResult result = (GeoSearchResult) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(PlacesSearchActivity.this, PlacesActivity.class);
                Bundle extras = new Bundle();
                extras.putDouble("latitude", result.getAddress().getLatitude());
                extras.putDouble("longitude", result.getAddress().getLongitude());
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        geo_autocomplete.addTextChangedListener(new SearchPlacesTextWatcher(geo_autocomplete_clear));

        geo_autocomplete_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                geo_autocomplete.setText("");
            }
        });
    }
}
