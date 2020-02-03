package com.marcoscouto.brazilianleague.client;

import com.marcoscouto.brazilianleague.models.Team;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Component
public class TeamClient {

    private final String host = "x-rapidapi-host";
    private final String hostValue = "api-football-v1.p.rapidapi.com";

    private final String key = "x-rapidapi-key";
    private final String keyValue = "804a18db84msh025369b8e1261c1p159576jsna1a63729f3fe";

    public List<Team> findAll(String url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestProperty(host, hostValue);
        conn.setRequestProperty(key, keyValue);
        InputStream in;

        if(conn.getResponseCode() == 200){
            List<Team> teams = new ArrayList<>();
            in = conn.getInputStream();
            JSONObject obj = new JSONObject(IOUtils.toString(in, Charset.forName("UTF-8")));
            obj = obj.getJSONObject("api");
            JSONArray array = obj.getJSONArray("teams");

            for (int i = 0; i < array.length(); i++) {
                teams.add(
                        new Team(
                                array.getJSONObject(i).getLong("team_id"),
                                array.getJSONObject(i).getString("name"),
                                array.getJSONObject(i).getString("logo")

                        ));
            }
            return teams;
        }

        return null;
    }
}
