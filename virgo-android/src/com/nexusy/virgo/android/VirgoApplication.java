package com.nexusy.virgo.android;

import android.app.Application;

/**
 * @author lan
 * @since 2014-3-26
 */
public class VirgoApplication extends Application {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
