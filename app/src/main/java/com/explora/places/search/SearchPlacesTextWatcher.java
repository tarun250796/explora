package com.explora.places.search;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by sumit.khandelwal1 on 06-Jan-18.
 */

public class SearchPlacesTextWatcher implements TextWatcher{

    private ImageView geo_autocomplete_clear;

    SearchPlacesTextWatcher(ImageView view){
        geo_autocomplete_clear = view;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() > 0) {
            geo_autocomplete_clear.setVisibility(View.VISIBLE);
        } else {
            geo_autocomplete_clear.setVisibility(View.GONE);
        }
    }
}
