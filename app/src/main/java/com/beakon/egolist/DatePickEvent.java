package com.beakon.egolist;

/**
 * Created by Beakon on 3/10/2017.
 */

public class DatePickEvent {
    public final int year, month, day;

    public DatePickEvent(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
}
