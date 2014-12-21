package com.nexusy.virgo.android;

import android.app.Application;

/**
 * @author lan
 * @since 2014-3-26
 */
public class VirgoApplication extends Application {
    
    private boolean isChanged;

    private String username;

    public synchronized boolean isChanged() {
        return isChanged;
    }

    public synchronized void setChanged(boolean isChanged) {
        this.isChanged = isChanged;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    

}
