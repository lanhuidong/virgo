package com.nexusy.virgo.android.util;

/**
 * @author lan
 * @since 2014-12-14
 */
public class StringUtils {
    
    public static boolean isBlank(String s){
        boolean result = false;
        s = s.trim();
        if(s == null || s.length() == 0){
            result = true;
        }
        return result;
    }

}
