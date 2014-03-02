package com.nexusy.virgo.android.http;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nexusy.virgo.android.model.Bill;

/**
 * @author lan
 * @since 2014-3-2
 */
public class JSONToBean {

    public List<Bill> parseHttpResponse(HttpResponse response) {
        try {
            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                String jsonString = EntityUtils.toString(response.getEntity());
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                List<Bill> bills = gson.fromJson(jsonString, new TypeToken<List<Bill>>() {}.getType());
                return bills;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
