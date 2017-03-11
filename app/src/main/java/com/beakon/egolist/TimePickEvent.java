package com.beakon.egolist;

/**
 * Created by Beakon on 3/7/2017.
 */

public class TimePickEvent {
    public final int hour, minute;

    public TimePickEvent(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }
}