package com.example.android.help;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static com.example.android.help.common.Constants.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Showing logo on Action Bar.
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
            getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        }
    }

    /**
     * onClick Listener for google icon and pass index when clicked.
     *
     * @param view
     */
    public void googleClick(View view) {
        startNewActivity(GOOGLE_INDEX);
    }

    /**
     * onClick Listener for play store icon and pass index when clicked.
     *
     * @param view
     */
    public void playStoreClick(View view) {
        startNewActivity(PLAYSTORE_INDEX);
    }

    /**
     * onClick Listener for facebook icon and pass index when clicked.
     *
     * @param view
     */
    public void facebookClick(View view) {
        startNewActivity(FACEBOOK_INDEX);
    }

    /**
     * onClick Listener for hangout icon and pass index when clicked.
     *
     * @param view
     */
    public void hangoutClick(View view) {
        startNewActivity(HANGOUT_INDEX);
    }

    /**
     * onClick Listener for map icon and pass index when clicked.
     *
     * @param view
     */
    public void mapsClick(View view) {
        startNewActivity(MAPS_INDEX);
    }

    /**
     * onClick Listener for twitter icon and pass index when clicked.
     *
     * @param view
     */
    public void twitterClick(View view) {
        startNewActivity(TWITTER_INDEX);
    }

    /**
     * Starts new activity whenever any icon is clicked.
     *
     * @param index is to find out which icon was clicked.
     */
    public void startNewActivity(int index) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra("key", index);
        startActivity(intent);
    }


}
