package com.example.android.help.common;

import android.app.Activity;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.Display;

/**
 * This contains generic methods to be used throughout application.
 */
public class Utils {
    /**
     * Returns the size of the device display.
     *
     * @param activity Activity
     * @return Point
     */
    public static Point getDisplayDimensions(Activity activity) {
        // Get display size
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    /**
     * Returns the height of action bar of that activity.
     *
     * @param activity Activity
     * @return Height.
     */
    public static int getActionBarHeight(Activity activity) {
        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        if (activity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, activity.getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }
}
