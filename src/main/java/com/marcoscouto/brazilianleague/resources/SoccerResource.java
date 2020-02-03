package com.marcoscouto.brazilianleague.resources;

import com.marcoscouto.brazilianleague.client.SoccerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/soccer")
public class SoccerResource {

    @Autowired
    private SoccerClient soccerClient;

    @GetMapping
    public ResponseEntity showAll(){
        try {
            String url = "https://api-football-v1.p.rapidapi.com/v2/leagues/country/brazil/2019";
            return ResponseEntity.ok().body(soccerClient.showAll(url));
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
