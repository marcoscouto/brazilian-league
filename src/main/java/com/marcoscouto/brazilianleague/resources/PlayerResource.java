package com.marcoscouto.brazilianleague.resources;

import com.marcoscouto.brazilianleague.client.PlayerClient;
import com.marcoscouto.brazilianleague.models.Player;
import com.marcoscouto.brazilianleague.models.TopScore;
import com.marcoscouto.brazilianleague.services.TopScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping(value = "/players")
public class PlayerResource {

    @Autowired
    private PlayerClient playerClient;

    @Autowired
    private TopScoreService topScoreService;

    @GetMapping(value = "/team/{id}")
    public ResponseEntity<Set<Player>> findByTeam(@PathVariable String id) throws IOException {
        Set<Player> players = playerClient.findByTeam(id);

        if(players == null)
            return ResponseEntity.status(404).build();
        return ResponseEntity.ok().body(players);
    }

    @GetMapping(value = "/topscores")
    public ResponseEntity<List<TopScore>> findTopScores() throws IOException {
        List<TopScore> topScores = topScoreService.findAll();
        return ResponseEntity.ok().body(topScores);
    }

}
