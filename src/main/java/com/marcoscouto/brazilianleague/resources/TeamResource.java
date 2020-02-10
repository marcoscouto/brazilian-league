package com.marcoscouto.brazilianleague.resources;

import com.marcoscouto.brazilianleague.client.PlayerClient;
import com.marcoscouto.brazilianleague.client.TeamClient;
import com.marcoscouto.brazilianleague.client.TeamStatisticClient;
import com.marcoscouto.brazilianleague.models.Team;
import com.marcoscouto.brazilianleague.models.TeamStatistic;
import com.marcoscouto.brazilianleague.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/teams")
public class TeamResource {

    @Autowired
    private TeamClient teamClient;

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamStatisticClient teamStatisticClient;

    @Autowired
    private PlayerClient playerClient;

    @GetMapping
    public ResponseEntity<List<Team>> findAll() {
        List<Team> teams = teamService.findAll();
        return ResponseEntity.ok().body(teams);
    }

    @GetMapping(value = "/statistic/{id}")
    public ResponseEntity<TeamStatistic> findById(@PathVariable Integer id) throws IOException {
        TeamStatistic teamStatistic = teamStatisticClient.findById(id);
        if(teamStatistic == null)  return ResponseEntity.status(404).build();
        return ResponseEntity.ok().body(teamStatistic);
    }

}
