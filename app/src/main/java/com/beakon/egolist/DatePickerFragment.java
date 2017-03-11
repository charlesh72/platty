package com.beakon.egolist;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.DatePicker;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Beakon on 3/10/2017.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public static final String YEAR_ARG = "year";
    public static final String MONTH_ARG = "month";
    public static final String DAY_ARG = "day";
    private int year, month, day;

    /**
     * Supply the construction arguments for this fragment.  This can only
     * be called before the fragment has been attached to its activity; that
     * is, you should call it immediately after constructing the fragment.  The
     * arguments supplied here will be retained across fragment destroy and
     * creation.
     *
     * @param args
     */
    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        year = args.getInt(YEAR_ARG);
        month = args.getInt(MONTH_ARG);
        day = args.getInt(DAY_ARG);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        EventBus.getDefault().post(new DatePickEvent(year, month, dayOfMonth));
    }
}
