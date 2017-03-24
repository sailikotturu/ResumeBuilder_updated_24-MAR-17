package com.example.sailik.resumebuilder_24_feb;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by saili.k on 20-03-2017.
 */

public class SettingsFragment extends PreferenceFragment
{

    AppCompatDelegate mDelegate;


    @Override
    public void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.user_settings);
        mDelegate = AppCompatDelegate.create(getActivity(),null);
        ActionBar actionBar = mDelegate.getSupportActionBar();
       if (actionBar!=null) {
           actionBar.setTitle("About");

       }
        PackageManager manager = getActivity().getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(
                    getActivity().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = info.versionName;
        Preference versionpreference = findPreference("version_info");
        versionpreference.setSummary(String.format("%s", version));
        Preference pref = findPreference("logout_btn");
        pref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

            @Override
            public boolean onPreferenceClick(Preference preference) {
                // TODO Auto-generated method
                Intent intent=new Intent();
                intent.putExtra("MESSAGE","logout");
                getActivity().setResult(2,intent);
                getActivity().finish();
                return false;
            }
        });
    }

}