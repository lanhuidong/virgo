package com.nexusy.virgo.android.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

/**
 * @author lan
 * @since 2014-3-1
 */
public class VirgoHttpClient {

    private static HttpClient initHttpClient() {
        SchemeRegistry sr = new SchemeRegistry();
        Scheme scheme = new Scheme("http", PlainSocketFactory.getSocketFactory(), 80);
        sr.register(scheme);
        HttpParams httpParams = new BasicHttpParams();
        ClientConnectionManager cm = new ThreadSafeClientConnManager(httpParams, sr);
        return new DefaultHttpClient(cm, httpParams);
    }

    private final static HttpClient httpclient = initHttpClient();
    
    public static HttpClient getHttpClient(){
        return httpclient;
    }
    
    public static HttpResponse post(String url, Map<String, String> params) {

        HttpPost httppost = new HttpPost(url);

        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            for (String key : params.keySet()) {
                if (key != null && key.trim().length() != 0) {
                    nameValuePairs.add(new BasicNameValuePair(key, params
                            .get(key)));
                }
            }
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf8"));
            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            return response;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String parseHttpResponseToString(HttpResponse response){
        try {
            if(response != null && response.getStatusLine().getStatusCode() == 200){
                return EntityUtils.toString(response.getEntity());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
