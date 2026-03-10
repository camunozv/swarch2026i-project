package com.carlos.gamificationservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class EventsController {

    private double boilerPlate() {
        return 0;
    }

    // Post user streak: For the first time a user begins a new streak.
    @PostMapping("/")
    public ResponseEntity<Double> postNewStreak() {
        return new ResponseEntity<>(boilerPlate(), HttpStatus.CREATED);
    }

    //
}
