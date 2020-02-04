package com.marcoscouto.brazilianleague.services;

import com.marcoscouto.brazilianleague.models.TopScore;
import com.marcoscouto.brazilianleague.repositories.TopScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopScoreService {

    @Autowired
    private TopScoreRepository topScoreRepository;

    public List<TopScore> findAll(){
        return topScoreRepository.findAll();
    }

    public void save(TopScore topScore){
        topScoreRepository.save(topScore);
    }
}
