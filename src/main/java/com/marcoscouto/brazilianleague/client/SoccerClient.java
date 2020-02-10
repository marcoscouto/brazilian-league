package com.marcoscouto.brazilianleague.client;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;

@Component
public class SoccerClient extends AbstractClient{

    public String showAll(String url) throws IOException {
        HttpURLConnection conn = connection("");

        if(conn == null) return null;

        if(conn.getResponseCode() == 200){
            JSONObject obj = new JSONObject(
                    IOUtils.toString(conn.getInputStream(), Charset.forName("UTF-8")));
            conn.disconnect();
            return obj.toString();
       }

       return null;
    }

}
