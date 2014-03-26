package com.nexusy.virgo.android;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

/**
 * @author lan
 * @since 2014-3-26
 */
public class VirgoApplication extends Application {

    private List<Activity> activities;

    public synchronized void addActivity(Activity activity) {
        if (activities == null) {
            activities = new ArrayList<Activity>();
        }
        activities.add(activity);
    }

    public synchronized void removeActivity(Activity activity) {
        if (activities != null) {
            activities.remove(activity);
        }
    }

    public synchronized void clear() {
        if (activities != null) {
            for (Activity activity : activities) {
                activity.finish();
            }
        }
    }

}
