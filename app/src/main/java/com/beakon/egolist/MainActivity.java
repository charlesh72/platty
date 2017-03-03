package com.beakon.egolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button parseLoginB = (Button)(findViewById(R.id.loginBMain));

        ParseUser user = ParseUser.getCurrentUser();
        if (user.isAuthenticated()) {

        } else {
            ParseLoginBuilder builder = new ParseLoginBuilder(MainActivity.this);
            startActivityForResult(builder.build(), 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseUser user = ParseUser.getCurrentUser();
        if (user != null) {

        }
    }
}
