package com.carlos.gamificationservice.services;

import com.carlos.gamificationservice.dtos.dtosImpl.UserStreakDTO;
import com.carlos.gamificationservice.models.UserStreak;

public interface UserStreakService {

    UserStreakDTO registerUserActivity(UserStreak userActivity);
}
