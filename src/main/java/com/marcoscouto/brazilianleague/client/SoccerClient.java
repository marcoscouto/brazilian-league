package com.marcoscouto.brazilianleague.client;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

@Component
public class SoccerClient {

    private final String host = "x-rapidapi-host";
    private final String hostValue = "api-football-v1.p.rapidapi.com";

    private final String key = "x-rapidapi-key";
    private final String keyValue = "804a18db84msh025369b8e1261c1p159576jsna1a63729f3fe";

    public String showAll(String url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestProperty(host, hostValue);
        conn.setRequestProperty(key, keyValue);
        InputStream in;

        if(conn.getResponseCode() == 200){
            System.out.println("here");
            in = conn.getInputStream();
            JSONObject obj = new JSONObject(IOUtils.toString(in, Charset.forName("UTF-8")));
            return obj.toString();
       }

       return null;
    }

}
