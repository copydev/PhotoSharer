package com.example.idtyp.instaclone;


import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class StarterApplication extends Application {
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("3805JIRVl2ojg5wQHRTAyVK3HgyDFTX6Vt24LDq1")
                .clientKey("fYXxryWraMpiHl0fJuyJx5ZaBHkqCQ6Krkeg5zxQ")
                .server("https://parseapi.back4app.com")
                .build()
        );

        //ParseInstallation.getCurrentInstallation().saveInBackground();



        //ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);

        ParseACL.setDefaultACL(defaultACL,true);




//        String myCustomKey1Value = "foo";
//        Integer myCustomKey2Value = 999;
//
//        ParseObject myNewObject = new ParseObject("MyCustomClassName");
//        myNewObject.put("myCustomKey1Name", myCustomKey1Value);
//        myNewObject.put("myCustomKey2Name", myCustomKey2Value);
//
//        // Saves the new object.
//        // Notice that the SaveCallback is totally optional!
//        myNewObject.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                // Here you can handle errors, if thrown. Otherwise, "e" should be null
//            }
//        });
    }
}
