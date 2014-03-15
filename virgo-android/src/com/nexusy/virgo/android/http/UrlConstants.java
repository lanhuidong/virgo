package com.nexusy.virgo.android.http;

/**
 * @author lan
 * @since 2014-3-1
 */
public interface UrlConstants {

    String HOSTNAME = "https://bill.nexusy.com/";

    String SIGN_URL = HOSTNAME + "signup";
    
    String LOGIN_URL = HOSTNAME + "login";

    String QUERY_BILL_URL = HOSTNAME + "api/bill";
    
    String ADD_BILL_URL = HOSTNAME + "api/bill/add";
    
    String DEL_BILL_URL = HOSTNAME + "api/bill/delete/";
}
