package com.marcoscouto.brazilianleague.client;

import com.marcoscouto.brazilianleague.models.Team;
import com.marcoscouto.brazilianleague.services.TeamService;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private TeamService teamService;

    private final String url = "https://api-football-v1.p.rapidapi.com/v2/teams/league/";

    @Value("${api.host}")
    private String host;

    @Value("${api.hostValue}")
    private String hostValue;

    @Value("${api.key}")
    private String key;

    @Value("${api.keyValue}")
    private String keyValue;

    public List<Team> findAll(String league) throws IOException {
        String link = url + league;
        HttpURLConnection conn = (HttpURLConnection) new URL(link).openConnection();
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
                Team team = new Team(
                        array.getJSONObject(i).getLong("team_id"),
                        array.getJSONObject(i).getString("name"),
                        array.getJSONObject(i).getString("logo"));
                teams.add(team);
            }
            return teams;
        }

        return null;
    }
}
