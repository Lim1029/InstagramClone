package com.mingkang.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("mgo27vyvqMOkTJ8xApNl4LTpbBo1IREZX3MLgnW2")
                // if defined
                .clientKey("I9gOSuVbP0KCkiZxyKPxfDuwXXX3KxfiZVCx2FBN")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
