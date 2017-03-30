package com.beakon.platty;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class ContactsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.contactsFLPlaceholder, ContactsFragment.newInstance());
        ft.commit();
    }
}
