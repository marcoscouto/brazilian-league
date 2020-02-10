package com.marcoscouto.brazilianleague.client;

import com.marcoscouto.brazilianleague.models.Team;
import com.marcoscouto.brazilianleague.services.TeamService;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Component
public class TeamClient extends AbstractClient{

    @Autowired
    private TeamService teamService;

    public List<Team> findAll(String league) throws IOException {

        String endpoint = "/teams/league/" + league;

        HttpURLConnection conn = connection(endpoint);

        if(conn.getResponseCode() == 200){
            List<Team> teams = new ArrayList<>();
            JSONObject obj = new JSONObject(
                    IOUtils.toString(conn.getInputStream(), Charset.forName("UTF-8")));
            JSONArray array =
                    obj
                    .getJSONObject("api")
                    .getJSONArray("teams");

            for (int i = 0; i < array.length(); i++) {
                Team team = new Team(
                        array.getJSONObject(i).getLong("team_id"),
                        array.getJSONObject(i).getString("name"),
                        array.getJSONObject(i).getString("logo"));
                teams.add(team);
            }
            conn.disconnect();
            return teams;
        }

        return null;
    }
}
