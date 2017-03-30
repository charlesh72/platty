package com.beakon.platty;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.contactsFLPlaceholder, ContactsFragment.newInstance());
        ft.commit();
    }
}
