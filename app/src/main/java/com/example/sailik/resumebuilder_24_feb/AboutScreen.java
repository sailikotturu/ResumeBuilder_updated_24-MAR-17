package com.example.sailik.resumebuilder_24_feb;


import android.preference.PreferenceActivity;

import android.os.Bundle;



public class AboutScreen extends PreferenceActivity {

    //public static SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();


    }



}