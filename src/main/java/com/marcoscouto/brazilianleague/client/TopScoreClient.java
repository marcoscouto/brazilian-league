package com.marcoscouto.brazilianleague.client;

import com.marcoscouto.brazilianleague.models.TopScore;
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
public class TopScoreClient {

    private final String url = "https://api-football-v1.p.rapidapi.com/v2/topscorers/";
    private final String team = "https://api-football-v1.p.rapidapi.com/v2/teams/team/";

    private final String host = "x-rapidapi-host";
    private final String hostValue = "api-football-v1.p.rapidapi.com";

    private final String key = "x-rapidapi-key";
    private final String keyValue = "804a18db84msh025369b8e1261c1p159576jsna1a63729f3fe";

    public List<TopScore> findTopScore(String league) throws IOException {
        String link = url + league;
        HttpURLConnection conn = (HttpURLConnection) new URL(link).openConnection();
        conn.setRequestProperty(host, hostValue);
        conn.setRequestProperty(key, keyValue);
        InputStream in;

        if(conn.getResponseCode() == 200){
            List<TopScore> topScores = new ArrayList<>();
            in = conn.getInputStream();
            JSONObject obj = new JSONObject(IOUtils.toString(in, Charset.forName("UTF-8")));
            obj = obj.getJSONObject("api");
            JSONArray array = obj.getJSONArray("topscorers");

            for (int i = 0; i < array.length(); i++) {
                System.out.println();
                int id = array.getJSONObject(i).getInt("team_id");
                conn = (HttpURLConnection) new URL(team + id).openConnection();
                conn.setRequestProperty(host, hostValue);
                conn.setRequestProperty(key, keyValue);
                in = conn.getInputStream();
                JSONObject obj2 = new JSONObject(IOUtils.toString(in, Charset.forName("UTF-8")));
                String teamLogo = obj2.getJSONObject("api")
                        .getJSONArray("teams")
                        .getJSONObject(0)
                        .getString("logo");

                topScores.add(
                        new TopScore(
                                array.getJSONObject(i).getString("player_name"),
                                array.getJSONObject(i).getJSONObject("goals").optInt("total",0),
                                array.getJSONObject(i).getJSONObject("goals").optInt("assists", 0),
                                teamLogo
                        ));
            }
            return topScores;
        }

        return null;
    }

}