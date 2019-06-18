/*
* itemAdapter.java
*
* Description: This class creates the list of songs shown on the
* main activity after they are retrieved from the database. The
* list is dynamic in that it will grow depending on the number of
* songs on the db*/
package com.example.MusicApp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemAdapter extends BaseAdapter {
    //declare variables
    LayoutInflater mInflator;
    Map<String, Double> map;
    List<String> songs;
    List<Double> duration;
    //constructor method. takes in the context
    //from the main class and the map of songs and durations.
    //stores the songs and durations from the map as seperate
    //array lists
    public ItemAdapter(Context c, Map m){
        mInflator = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        map = m;
        songs = new ArrayList<String>(map.keySet());
        duration = new ArrayList<Double>(map.values());
    }
    //returns map size
    @Override
    public int getCount() {
        return map.size();
    }
    //returns song at current position
    @Override
    public Object getItem(int position) {
        return songs.get(position);
    }
    //returns itemID
    @Override
    public long getItemId(int position) {
        return position;
    }
    //uses the layout inflator to populate the listview on the main page
    //with the layout specified in the item_layout.xml
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = mInflator.inflate(R.layout.item_layout, null);
        TextView songTextView = (TextView) v.findViewById(R.id.songTextView);
        TextView durationTextView = (TextView) v.findViewById(R.id.durationTextView);

        songTextView.setText(songs.get(position));
        durationTextView.setText(duration.get(position).toString());

        return v;
    }
}
