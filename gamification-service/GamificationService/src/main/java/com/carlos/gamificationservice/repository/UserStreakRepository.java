package com.carlos.gamificationservice.repository;

import com.carlos.gamificationservice.models.UserStreak;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStreakRepository extends JpaRepository<UserStreak, Integer> {
    // Here we must declare the methods for the db interaction.


}
