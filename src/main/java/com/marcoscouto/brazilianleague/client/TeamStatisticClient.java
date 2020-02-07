package com.marcoscouto.brazilianleague.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcoscouto.brazilianleague.models.Team;
import com.marcoscouto.brazilianleague.models.TeamStatistic;
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
import java.util.List;

@Component
public class TeamStatisticClient {

    private final String url = "https://api-football-v1.p.rapidapi.com/v2/teams/team/";
    private final String urlStatistic = "https://api-football-v1.p.rapidapi.com/v2/statistics/357/";

    @Value("${api.host}")
    private String host;

    @Value("${api.hostValue}")
    private String hostValue;

    @Value("${api.key}")
    private String key;

    @Value("${api.keyValue}")
    private String keyValue;

    public TeamStatistic findById(Integer id) throws IOException {
        String link = url + id;
        HttpURLConnection conn = (HttpURLConnection) new URL(link).openConnection();
        conn.setRequestProperty(host, hostValue);
        conn.setRequestProperty(key, keyValue);
        InputStream in;

        if(conn.getResponseCode() == 200){
            in = conn.getInputStream();
            JSONObject obj = new JSONObject(IOUtils.toString(in, Charset.forName("UTF-8")));
            obj = obj.getJSONObject("api");
            JSONArray array = obj.getJSONArray("teams");

            TeamStatistic teamStatistic = getStatistics(id);

            if(array.isEmpty()) return null;

            teamStatistic.setName(array.getJSONObject(0).getString("name"));
            teamStatistic.setPosition(getPosition(id));

            return teamStatistic;
        }

        return null;
    }

    @JsonIgnore
    private TeamStatistic getStatistics(Integer id) throws IOException {
        String link = urlStatistic + id;
        HttpURLConnection conn = (HttpURLConnection) new URL(link).openConnection();
        conn.setRequestProperty(host, hostValue);
        conn.setRequestProperty(key, keyValue);
        InputStream in = conn.getInputStream();
        JSONObject obj = new JSONObject(IOUtils.toString(in, Charset.forName("UTF-8")));
        obj = obj.getJSONObject("api")
                .getJSONObject("statistics")
                .getJSONObject("matchs");


         TeamStatistic teamStatistic = new TeamStatistic();
         teamStatistic.setVictory(obj.getJSONObject("wins").getInt("total"));
         teamStatistic.setDraw(obj.getJSONObject("draws").getInt("total"));
         teamStatistic.setLose(obj.getJSONObject("loses").getInt("total"));

         return teamStatistic;
}

    @JsonIgnore
    private Integer getPosition(Integer id){
        if(id == 134) return 5;
        else if(id == 131) return 8;
        else if(id == 118) return 11;
        else if(id == 133) return 12;
        else if(id == 1062) return 13;
        else if(id == 145) return 20;
        else if(id == 129) return 16;
        else if(id == 150) return 18;
        else if(id == 132) return 19;
        else if(id == 119) return 7;
        else if(id == 127) return 1;
        else if(id == 135) return 17;
        else if(id == 124) return 14;
        else if(id == 151) return 10;
        else if(id == 130) return 4;
        else if(id == 128) return 2;
        else if(id == 121) return 3;
        else if(id == 154) return 9;
        else if(id == 126) return 6;
        else if(id == 120) return 15;
        return 0;
    }

}
