package com.marcoscouto.brazilianleague.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_team")
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long code;
    private String name;
    private String urlLogo;

    public Team() {
    }

    public Team(Long id, String name, String urlLogo) {
        this.code = id;
        this.name = name;
        this.urlLogo = urlLogo;
    }

    public Long getId() {
        return code;
    }

    public void setId(Long id) {
        this.code = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(code, team.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
