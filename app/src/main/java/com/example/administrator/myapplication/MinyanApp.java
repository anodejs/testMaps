package com.example.administrator.myapplication;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by amitmach on 8/9/2014.
 */
public class MinyanApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


    }

    public boolean hasUserAccount() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        String name = settings.getString("name", null);
        return name != null;
    }

    public void setUserAccount(String email, String name) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("email", email);
        editor.putString("name", name);
        editor.commit();
    }


}
