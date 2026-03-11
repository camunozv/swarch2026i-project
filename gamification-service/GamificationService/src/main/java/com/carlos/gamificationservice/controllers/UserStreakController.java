package com.carlos.gamificationservice.controllers;

import com.carlos.gamificationservice.dtos.dtosImpl.UserStreakDTO;
import com.carlos.gamificationservice.services.UserStreakService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userStreak")
@AllArgsConstructor
public class UserStreakController {

    private UserStreakService userStreakService;

    public UserStreakController() {
    }

    @PostMapping
    public ResponseEntity<UserStreakDTO> postUserStreak(@RequestParam String userName) {
        try {
            UserStreakDTO registered = userStreakService.registerUserActivity(userName);
            return new ResponseEntity<>(registered, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
