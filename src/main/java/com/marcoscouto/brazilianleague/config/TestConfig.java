package com.marcoscouto.brazilianleague.config;

import com.marcoscouto.brazilianleague.client.TeamClient;
import com.marcoscouto.brazilianleague.models.Team;
import com.marcoscouto.brazilianleague.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private TeamClient teamClient;

    @Autowired
    private TeamService teamService;

    @Override
    public void run(String... args) throws Exception {
        List<Team> teams = teamClient.findAll("357");
        teams.forEach(x -> teamService.save(x));
    }
}
