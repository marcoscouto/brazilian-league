package com.marcoscouto.brazilianleague.dto;

import com.marcoscouto.brazilianleague.models.Player;
import com.marcoscouto.brazilianleague.models.Team;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Component
public class TeamPlayersDTO implements Serializable {

    private String name;
    private String urLogo;
    private Set<Player> players;

    public TeamPlayersDTO() {
    }

    public TeamPlayersDTO(Team team) {
        this.name = team.getName();
        this.urLogo = team.getUrlLogo();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrLogo() {
        return urLogo;
    }

    public void setUrLogo(String urLogo) {
        this.urLogo = urLogo;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamPlayersDTO that = (TeamPlayersDTO) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
