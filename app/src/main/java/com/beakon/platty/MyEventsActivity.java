package com.beakon.platty;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class MyEventsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_events);


        String eventId = "placeholderid";

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Event");
        query.whereEqualTo("eventId", eventId);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.d("event", "Retrieved " + objects.size() + " events");
                    displayEvents(objects);
                } else {
                    Log.d("event", "Error: " + e.getMessage());
                }
            }
        });
    }

    private void displayEvents(List<ParseObject> objects) {
        EventAdapter adapter = new EventAdapter(this, objects);
        ListView listView = (ListView) findViewById(R.id.myEventsListView);
        listView.setAdapter(adapter);
    }
}