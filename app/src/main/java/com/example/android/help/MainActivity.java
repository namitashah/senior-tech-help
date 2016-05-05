package com.example.android.help;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import static com.example.android.help.common.Constants.BUNDLE_KEY_LINK_CLICK_TYPE;
import static com.example.android.help.common.Constants.FACEBOOK_INDEX;
import static com.example.android.help.common.Constants.GOOGLE_INDEX;
import static com.example.android.help.common.Constants.HANGOUT_INDEX;
import static com.example.android.help.common.Constants.MAPS_INDEX;
import static com.example.android.help.common.Constants.PLAYSTORE_INDEX;
import static com.example.android.help.common.Constants.TWITTER_INDEX;

/**
 * Shoes the main activity with options to choose various social networks
 * for which users need to know.
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Showing logo on Action Bar.
        setActionBarDetails(false);
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
        intent.putExtra(BUNDLE_KEY_LINK_CLICK_TYPE, index);
        startActivity(intent);
    }


}
