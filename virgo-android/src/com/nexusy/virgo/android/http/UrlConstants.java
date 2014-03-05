package com.nexusy.virgo.android.http;

/**
 * @author lan
 * @since 2014-3-1
 */
public interface UrlConstants {

    String HOSTNAME = "http://192.168.0.103/";

    String LOGIN_URL = HOSTNAME + "login";

    String QUERY_BILL_URL = HOSTNAME + "/api/bill";
    
    String ADD_BILL_URL = HOSTNAME + "/api/bill/add";
}
