package com.marcoscouto.brazilianleague.config;

import com.marcoscouto.brazilianleague.client.TeamClient;
import com.marcoscouto.brazilianleague.client.TopScoreClient;
import com.marcoscouto.brazilianleague.models.Team;
import com.marcoscouto.brazilianleague.models.TopScore;
import com.marcoscouto.brazilianleague.services.TeamService;
import com.marcoscouto.brazilianleague.services.TopScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
public class Config implements CommandLineRunner {

    @Autowired
    private TeamClient teamClient;

    @Autowired
    private TeamService teamService;

    @Autowired
    private TopScoreClient topScoreClient;

    @Autowired
    private TopScoreService topScoreService;

    @Override
    public void run(String... args) throws Exception {

        if(teamService.findAll() == null) {
            List<Team> teams = teamClient.findAll("357");
            teams.forEach(x -> teamService.save(x));
        }


        if(topScoreService.findAll() == null){
            List<TopScore> topScores = topScoreClient.findTopScore("357");
            topScores.forEach(x -> topScoreService.save(x));
        }

    }
}
