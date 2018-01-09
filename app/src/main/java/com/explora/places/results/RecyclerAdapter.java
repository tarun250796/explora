package com.explora.places.results;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.explora.places.R;
import com.explora.places.externalservice.models.Place;
import com.explora.places.externalservice.models.Places;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder> {
    private List<Place> places;
    private Context c;

    public RecyclerAdapter(Context c, List<Place> places) {
        this.places = places;
        this.c = c;
    }
    /*
    INITIALIZE VIEWHOLDER
     */
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(c).inflate(R.layout.activity_recycler_item,parent,false);
        return new MyHolder(v);
    }
    /*
    BIND
     */
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Place s= places.get(position);
        holder.nameTxt.setText(s.getName());
        holder.addressTxt.setText(s.getName());
        holder.distanceTxt.setText(s.getDistance());
        //holder.img.setImageResource();
        holder.ratingBar.setRating(s.getRating());
    }
    /*
    TOTAL SPACECRAFTS NUM
     */
    @Override
    public int getItemCount() {
        return places.size();
    }
    /*
    VIEW HOLDER CLASS
     */
    class MyHolder extends RecyclerView.ViewHolder
    {
        TextView nameTxt;
        TextView addressTxt;
        TextView distanceTxt;
        ImageView img;
        SimpleRatingBar ratingBar;
        public MyHolder(View itemView) {
            super(itemView);
            nameTxt= (TextView) itemView.findViewById(R.id.nameTxt);
            addressTxt= (TextView) itemView.findViewById(R.id.addressTxt);
            distanceTxt= (TextView) itemView.findViewById(R.id.distanceTxt);
            img= (ImageView) itemView.findViewById(R.id.spacecraftImage);
            ratingBar= (SimpleRatingBar) itemView.findViewById(R.id.ratingBarID);
        }
    }
}