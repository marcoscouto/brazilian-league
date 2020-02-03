package com.marcoscouto.brazilianleague.resources;

import com.marcoscouto.brazilianleague.client.PlayerClient;
import com.marcoscouto.brazilianleague.client.TeamClient;
import com.marcoscouto.brazilianleague.models.Player;
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

}
