package com.beakon.platty;

import android.app.Application;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.interceptors.ParseLogInterceptor;

/**
 * Created by Beakon on 3/3/2017.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("App",FacebookSdk.getApplicationSignature(this));

        Parse.enableLocalDatastore(this);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("myAppId")
                .clientKey("myMasterKey")
                .addNetworkInterceptor(new ParseLogInterceptor())
                .server("https://guarded-brushlands-88435.herokuapp.com/parse/")
                .build());

        ParseFacebookUtils.initialize(this);

        ParseUser.enableAutomaticUser();
        ParseUser.getCurrentUser().increment("RunCount");
        ParseUser.getCurrentUser().saveInBackground();

        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
