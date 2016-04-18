package com.example.android.legowords;

import android.app.Application;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import java.util.Locale;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Locale locale = new Locale("ab");
        Locale.setDefault(locale);
        Configuration config = getApplicationContext().getResources().getConfiguration();
        config.locale = locale;
        DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();
        getApplicationContext().getResources().updateConfiguration(config, metrics);
    }
}
