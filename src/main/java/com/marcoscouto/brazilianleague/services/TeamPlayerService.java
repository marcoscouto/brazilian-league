package com.marcoscouto.brazilianleague.services;

import com.marcoscouto.brazilianleague.client.PlayerClient;
import com.marcoscouto.brazilianleague.dto.TeamPlayersDTO;
import com.marcoscouto.brazilianleague.models.Team;
import com.marcoscouto.brazilianleague.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TeamPlayerService {

    @Autowired
    private PlayerClient playerClient;

    @Autowired
    private TeamRepository teamRepository;

    public TeamPlayersDTO findTeamPlayers(String id) throws IOException {
        Team team = teamRepository.findByCode(Integer.parseInt(id));

        if(team == null) return null;

        TeamPlayersDTO teamPlayersDTO = new TeamPlayersDTO(team);
        teamPlayersDTO.setPlayers(playerClient.findByTeam(id));

        return teamPlayersDTO;
    }
}
