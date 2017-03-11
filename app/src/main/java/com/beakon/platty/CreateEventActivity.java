package com.beakon.platty;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class CreateEventActivity extends Activity {

    private Event mEvent;
    private TextView mEventTime, mEventDate;
    private final int PLACE_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        mEvent = new Event();

        mEventTime = (TextView) findViewById(R.id.createEventTVTime);
        mEventTime.setText(mEvent.getHour() + ":" + mEvent.getMinute());

        mEventDate = (TextView) findViewById(R.id.createEventTVDate);
        mEventDate.setText(mEvent.getMonth() + "-" + mEvent.getDay() + "-" + mEvent.getYear());

        Button getPlaceB = (Button) findViewById(R.id.createEventBGetPlace);
        getPlaceB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateEventActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        Button placePickerB = (Button) findViewById(R.id.createEventBPlacePicker);
        placePickerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(CreateEventActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onTimePickEvent(TimePickEvent event) {
        // TODO: 3/10/2017 Account for AM/PM
        mEventTime.setText(event.hour + ":" + event.minute);
        mEvent.setHour(event.hour);
        mEvent.setMinute(event.minute);
    }

    @Subscribe
    public void onDatePickEvent(DatePickEvent event) {
        mEventDate.setText((event.month + 1) + "-" + event.day + "-" + event.year);
        mEvent.setYear(event.year);
        mEvent.setMonth(event.month);
        mEvent.setDay(event.day);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this, data);
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
                mEvent.setLatLng(place.getLatLng());
                mEvent.setPlaceName(place.getName().toString());
            }
        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();

        Bundle args = new Bundle();
        args.putInt(DatePickerFragment.YEAR_ARG, mEvent.getYear());
        args.putInt(DatePickerFragment.MONTH_ARG, mEvent.getMonth());
        args.putInt(DatePickerFragment.DAY_ARG, mEvent.getDay());
        newFragment.setArguments(args);

        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();

        Bundle args = new Bundle();
        args.putInt(TimePickerFragment.HOUR_ARG, mEvent.getHour());
        args.putInt(TimePickerFragment.MINUTE_ARG, mEvent.getMinute());
        newFragment.setArguments(args);

        newFragment.show(getFragmentManager(), "timePicker");
    }
}
