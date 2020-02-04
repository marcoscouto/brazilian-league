package com.marcoscouto.brazilianleague.repositories;

import com.marcoscouto.brazilianleague.models.TopScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopScoreRepository extends JpaRepository<TopScore, Long> {
}
