package com.kosign.hiltdemo;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
