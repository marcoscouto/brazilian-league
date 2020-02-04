package com.marcoscouto.brazilianleague.services;

import com.marcoscouto.brazilianleague.models.Team;
import com.marcoscouto.brazilianleague.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public List<Team> findAll(){
        return teamRepository.findAll();
    }

    public Team findByCode(Integer code){
        return teamRepository.findByCode(code);
    }

    public void save(Team team){
        teamRepository.save(team);
    }

}
