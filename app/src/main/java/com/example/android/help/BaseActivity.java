package com.example.android.help;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

/**
 * This is for all other activities to extend.
 * All common method go here.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    /**
     * Sets the action bar properties.
     *
     * @param isUpButtonEnabled bool to tell if up button needs to be enabled.
     */
    protected void setActionBarDetails(boolean isUpButtonEnabled) {
        //Showing logo on Action Bar.
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
            getSupportActionBar().setIcon(R.mipmap.ic_launcher);
            if (isUpButtonEnabled) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }
}
