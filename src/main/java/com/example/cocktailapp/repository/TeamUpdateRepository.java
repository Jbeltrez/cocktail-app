package com.example.cocktailapp.repository;

import com.example.cocktailapp.model.TeamUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamUpdateRepository extends JpaRepository<TeamUpdate, Long> {
    // Custom query to find updates for a specific team
    List<TeamUpdate> findByTeamId(Long teamId);
}
