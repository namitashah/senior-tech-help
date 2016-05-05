package com.example.android.help;

import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.android.help.common.Utils;

import static com.example.android.help.common.Constants.BUNDLE_KEY_LINK_CLICK_TYPE;
import static com.example.android.help.common.Constants.FACEBOOK_INDEX;
import static com.example.android.help.common.Constants.FACEBOOK_VIDEO_URL;
import static com.example.android.help.common.Constants.GOOGLE_INDEX;
import static com.example.android.help.common.Constants.GOOGLE_VIDEO_URL;
import static com.example.android.help.common.Constants.HANGOUT_INDEX;
import static com.example.android.help.common.Constants.HANGOUT_VIDEO_URL;
import static com.example.android.help.common.Constants.MAPS_INDEX;
import static com.example.android.help.common.Constants.MAP_VIDEO_URL;
import static com.example.android.help.common.Constants.PLAYSTORE_INDEX;
import static com.example.android.help.common.Constants.PLAYSTORE_VIDEO_URL;
import static com.example.android.help.common.Constants.TWITTER_INDEX;
import static com.example.android.help.common.Constants.TWITTER_VIDEO_URL;

/**
 * DetailsActivity opens when any icon is clicked.
 * It contains video, and other details for how to use that particular social media.
 */
public class DetailsActivity extends BaseActivity {

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

        // Set action bar
        setActionBarDetails(true);

        //Checking which icon was clicked from MainActivity, using the parameter which was passed in the intent.
        setViewDetails(getIntent());
    }

    /**
     * Sets details of the view.
     *
     * @param intent Intent with which Activity was created.
     */
    private void setViewDetails(Intent intent) {
        if (intent != null && intent.hasExtra(BUNDLE_KEY_LINK_CLICK_TYPE)) {
            int type = intent.getIntExtra(BUNDLE_KEY_LINK_CLICK_TYPE, -1);
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
     * @param view View
     */
    public void startShowingVideo(View view) {
        // Hide initial views which are not needed right now.
        llBtnStartVideo.setVisibility(View.GONE);
        // Show the layout containing the video view.
        rlVideoView.setVisibility(View.VISIBLE);
        videoView.setVisibility(View.VISIBLE);

        // Sets the size of the view based on actionbar and device size.
        setVideoViewSize();

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
     * Sets the size of the video view.
     */
    private void setVideoViewSize() {
        // Get dimensions
        Point size = Utils.getDisplayDimensions(this);
        int width = size.x;
        int height = size.y;

        // Calculate ActionBar height
        int actionBarHeight = Utils.getActionBarHeight(this);

        //setting size for videoView.
        RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) videoView.getLayoutParams();
        param.height = height - 3 * actionBarHeight;
        param.width = width;
        videoView.setLayoutParams(param);

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


