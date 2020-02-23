package com.marcoscouto.brazilianleague.client;

import com.marcoscouto.brazilianleague.models.Player;
import com.marcoscouto.brazilianleague.repositories.TeamRepository;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

@Component
public class PlayerClient extends AbstractClient {

    public Set<Player> findByTeam(String team) throws IOException {

        String endpoint = "/players/team/" + team + "/2019";

        HttpURLConnection conn = connection(endpoint);

        if(conn == null) return null;

        if(conn.getResponseCode() == 200){
            Set<Player> players = new HashSet<>();
            JSONObject obj = new JSONObject(
                    IOUtils.toString(conn.getInputStream(), Charset.forName("UTF-8")));
            JSONArray array =
                    obj
                    .getJSONObject("api")
                    .getJSONArray("players");

            if(array.isEmpty()) return null;

            for (int i = 0; i < array.length(); i++) {
                players.add(
                        new Player(
                                array.getJSONObject(i).getString("player_name"),
                                array.getJSONObject(i).getString("position"),
                                array.getJSONObject(i).getInt("age")

                        ));
            }
            conn.disconnect();
            return players;
        }

        return null;
    }

}
