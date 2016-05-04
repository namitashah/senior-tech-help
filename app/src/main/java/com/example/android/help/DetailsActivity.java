package com.example.android.help;

import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import static com.example.android.help.common.Constants.*;

/**
 * DetailsActivity opens when any icon is clicked.
 * It contains video, and other details for how to use that particular social media.
 */
public class DetailsActivity extends AppCompatActivity {

    // Video view to show video
    VideoView videoView;
    // Layout containing button shown on top of the video view, which gets hidden later
    LinearLayout llBtnStartVideo;
    // Layout with video view
    RelativeLayout rlVideoView;
    // Progressbar to show till video gets loaded.
    ProgressBar progressBar;
    // String to get video URL.
    String videoUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Get reference tot he views.
        videoView = (VideoView) findViewById(R.id.videoView);
        llBtnStartVideo = (LinearLayout) findViewById(R.id.llBtnStartVideo);
        rlVideoView = (RelativeLayout) findViewById(R.id.rlVideo);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //Showing logo on Action Bar.
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //Checking which icon was clicked from MainActivity, using the parameter which was passed in the intent.
        if (getIntent() != null && getIntent().hasExtra("key")) {
            int type = getIntent().getIntExtra("key", -1);
            String tabHeading = "";
            String steps = "";

            if (type == GOOGLE_INDEX) {
                tabHeading = getString(R.string.google_heading);
                steps = getString(R.string.google_steps);
                videoUrl = GOOGLE_VIDEO_URL;

            } else if (type == PLAYSTORE_INDEX) {
                tabHeading = getString(R.string.playstore_heading);
                steps = getString(R.string.playstore_steps);
                videoUrl = PLAYSTORE_VIDEO_URL;

            } else if (type == FACEBOOK_INDEX) {
                tabHeading = getString(R.string.facebook_heading);
                steps = getString(R.string.facebook_steps);
                videoUrl = FACEBOOK_VIDEO_URL;

            } else if (type == HANGOUT_INDEX) {
                tabHeading = getString(R.string.hangout_heading);
                steps = getString(R.string.hangout_steps);
                videoUrl = HANGOUT_VIDEO_URL;

            } else if (type == MAPS_INDEX) {
                tabHeading = getString(R.string.maps_heading);
                steps = getString(R.string.maps_steps);
                videoUrl = MAP_VIDEO_URL;

            } else if (type == TWITTER_INDEX) {
                tabHeading = getString(R.string.twitter_heading);
                steps = getString(R.string.twitter_steps);
                videoUrl = TWITTER_VIDEO_URL;
            }
            //setting the heading text for different icon selected.
            setHeadingText(tabHeading);

            //setting the details of the app.
            setSteps(steps);
        }
    }

    /**
     * This will display videos for each selected icon.
     *
     * @param view
     */
    public void startShowingVideo(View view) {
        // Hide initial views which are not needed right now.
        llBtnStartVideo.setVisibility(View.GONE);
        // Show the layout containing the video view.
        rlVideoView.setVisibility(View.VISIBLE);
        videoView.setVisibility(View.VISIBLE);
        // Get display size
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        // Calculate ActionBar height
        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }

        //setting size for videoView.
        RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) videoView.getLayoutParams();
        param.height = height - 3 * actionBarHeight;
        param.width = width;
        videoView.setLayoutParams(param);

        // Initialize media controller.
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        // Set settings for video.
        try {
            videoView.setVideoURI(Uri.parse(videoUrl));
        } catch (Exception e) {
            Log.e("TAG", "Exception showing video");
        }
        videoView.setMediaController(mediaController);

        // Start video.
        videoView.start();

        // This is to show progress bar till the time video is being loaded.
        // Otherwise there is just blank screen if video takes time to load.
        showProgressBar();
        // When video is ready and loaded, hide the progress bar.
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mp) {
                hideProgressBar();
            }
        });

    }

    /**
     * //setting the heading text for different icon selected.
     *
     * @param headingText
     */
    public void setHeadingText(String headingText) {
        TextView headingTextView = (TextView) findViewById(R.id.textView);
        headingTextView.setText(headingText);
    }

    /**
     * setting the details of the app.
     *
     * @param step
     */
    public void setSteps(String step) {
        TextView stepTextView = (TextView) findViewById(R.id.stepTextView);
        stepTextView.setText("Steps : \n" + step);
    }

    /**
     * * Shows progress bar.
     */
    private void showProgressBar() {
        rlVideoView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * Hides progress bar.
     */
    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }


    /**
     * To close activity when up button pressed.
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}


