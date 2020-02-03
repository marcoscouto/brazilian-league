package com.marcoscouto.brazilianleague.resources;

import com.marcoscouto.brazilianleague.client.TeamClient;
import com.marcoscouto.brazilianleague.models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/teams")
public class TeamResource {

    @Autowired
    private TeamClient teamClient;

    @GetMapping
    public ResponseEntity<List<Team>> findAll() throws IOException {
        List<Team> teams= new ArrayList<>();
        int[] leagues = {357, 358, 1003, 1007};
        for (int i = 0; i < leagues.length; i++) {
            String url = "https://api-football-v1.p.rapidapi.com/v2/teams/league/" + leagues[i];
            teamClient.findAll(url).forEach(x -> teams.add(x));
        }
        Map map = new HashMap();
        map.put("teams", teams);
        return ResponseEntity.ok().body(teams);
    }

}
