package com.marcoscouto.brazilianleague.models;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

@Component
public class TeamStatistic implements Serializable {

    private String name;
    private Integer position;
    private Integer victory;
    private Integer draw;
    private Integer lose;

    public TeamStatistic() {
    }

    public TeamStatistic(String name, Integer position, Integer victory, Integer draw, Integer lose) {
        this.name = name;
        this.position = position;
        this.victory = victory;
        this.draw = draw;
        this.lose = lose;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getVictory() {
        return victory;
    }

    public void setVictory(Integer victory) {
        this.victory = victory;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getLose() {
        return lose;
    }

    public void setLose(Integer lose) {
        this.lose = lose;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamStatistic that = (TeamStatistic) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(position, that.position) &&
                Objects.equals(victory, that.victory) &&
                Objects.equals(draw, that.draw) &&
                Objects.equals(lose, that.lose);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position, victory, draw, lose);
    }
}
