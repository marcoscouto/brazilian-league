package com.marcoscouto.brazilianleague.client;

import com.marcoscouto.brazilianleague.models.TopScore;
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
public class TopScoreClient extends AbstractClient{

    @Autowired
    private TeamService teamService;

    public List<TopScore> findTopScore(String league) throws IOException {

        String endpoint = "/topscorers/" + league;

        HttpURLConnection conn = connection(endpoint);

        if(conn.getResponseCode() == 200){
            List<TopScore> topScores = new ArrayList<>();
            JSONObject obj = new JSONObject(
                    IOUtils.toString(conn.getInputStream(), Charset.forName("UTF-8")));
            JSONArray array =
                    obj
                    .getJSONObject("api")
                    .getJSONArray("topscorers");

            for (int i = 0; i < array.length(); i++) {
                int id = array.getJSONObject(i).getInt("team_id");
                topScores.add(
                        new TopScore(
                                array.getJSONObject(i).getString("player_name"),
                                array.getJSONObject(i).getJSONObject("goals").optInt("total",0),
                                array.getJSONObject(i).getJSONObject("goals").optInt("assists", 0),
                                teamService.findByCode(id).getUrlLogo()
                        ));
            }

            conn.disconnect();

            return topScores;
        }

        return null;
    }

}
