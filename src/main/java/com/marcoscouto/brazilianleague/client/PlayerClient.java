package com.marcoscouto.brazilianleague.client;

import com.marcoscouto.brazilianleague.models.Player;
import com.marcoscouto.brazilianleague.models.Team;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PlayerClient {

    private final String url = "https://api-football-v1.p.rapidapi.com/v2/players/team/";

    @Value("${api.host}")
    private String host;

    @Value("${api.hostValue}")
    private String hostValue;

    @Value("${api.key}")
    private String key;

    @Value("${api.keyValue}")
    private String keyValue;

    public Set<Player> findByTeam(String team) throws IOException {
        String link = url + team + "/2019";
        HttpURLConnection conn = (HttpURLConnection) new URL(link).openConnection();
        conn.setRequestProperty(host, hostValue);
        conn.setRequestProperty(key, keyValue);
        InputStream in;

        if(conn.getResponseCode() == 200){
            Set<Player> players = new HashSet<>();
            in = conn.getInputStream();
            JSONObject obj = new JSONObject(IOUtils.toString(in, Charset.forName("UTF-8")));
            obj = obj.getJSONObject("api");
            JSONArray array = obj.getJSONArray("players");

            if(array.isEmpty()) return null;

            for (int i = 0; i < array.length(); i++) {
                players.add(
                        new Player(
                                array.getJSONObject(i).getString("player_name"),
                                array.getJSONObject(i).getString("position"),
                                array.getJSONObject(i).getInt("age")

                        ));
            }
            return players;
        }

        return null;
    }

}
