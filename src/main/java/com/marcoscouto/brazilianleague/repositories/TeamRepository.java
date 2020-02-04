package com.marcoscouto.brazilianleague.repositories;

import com.marcoscouto.brazilianleague.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query(value = "SELECT * FROM tb_team WHERE team.code = :code", nativeQuery = true)
    public Team findByCode(@Param("code") Integer code);

}
