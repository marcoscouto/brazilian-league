package com.marcoscouto.brazilianleague.resources;

import com.marcoscouto.brazilianleague.client.PlayerClient;
import com.marcoscouto.brazilianleague.client.TeamClient;
import com.marcoscouto.brazilianleague.client.TeamStatisticClient;
import com.marcoscouto.brazilianleague.models.Player;
import com.marcoscouto.brazilianleague.models.Team;
import com.marcoscouto.brazilianleague.models.TeamStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private TeamStatisticClient teamStatisticClient;

    @Autowired
    private PlayerClient playerClient;

    @GetMapping
    public ResponseEntity<List<Team>> findAll() throws IOException {
        List<Team> teams= new ArrayList<>();
        //int[] leagues = {357, 358, 1003, 1007};
        String serieA = "357";
        teamClient.findAll(serieA).forEach(x -> teams.add(x));
        return ResponseEntity.ok().body(teams);
    }

    @GetMapping(value = "/statistic/{id}")
    public ResponseEntity<TeamStatistic> findById(@PathVariable Integer id) throws IOException {
        TeamStatistic teamStatistic = teamStatisticClient.findById(id);
        if(teamStatistic == null)  return ResponseEntity.status(404).build();
        return ResponseEntity.ok().body(teamStatistic);
    }

}
