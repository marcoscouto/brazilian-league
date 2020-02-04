package com.marcoscouto.brazilianleague.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_topscore")
public class TopScore implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer goal;
    private Integer assists;
    private String urlTeamLogo;

    public TopScore() {
    }

    public TopScore(String name, Integer goal, Integer assists, String urlTeamLogo) {
        this.name = name;
        this.goal = goal;
        this.assists = assists;
        this.urlTeamLogo = urlTeamLogo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public Integer getAssistance() {
        return assists;
    }

    public void setAssistance(Integer assistance) {
        this.assists = assistance;
    }

    public String getUrlLogo() {
        return urlTeamLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlTeamLogo = urlLogo;
    }
}
