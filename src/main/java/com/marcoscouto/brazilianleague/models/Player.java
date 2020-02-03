package com.marcoscouto.brazilianleague.models;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Player implements Serializable {

    private String name;
    private String position;
    private Integer age;

    public Player() {
    }

    public Player(String name, String position, Integer age) {
        this.name = name;
        this.position = position;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


}
