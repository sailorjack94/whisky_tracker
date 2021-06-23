package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> getWhiskies(@RequestParam(name = "distillery", required = false) String distillery, @RequestParam(name = "age", required = false) Integer age) {
        if (age != null && distillery == null) {
            return new ResponseEntity<>(whiskyRepository.findByYear(age), HttpStatus.OK);
        } else
            return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/whisky/{id}")
    public ResponseEntity getWhisky(@PathVariable Long id) {
        return new ResponseEntity<>(whiskyRepository.findById(id), HttpStatus.OK);
    }

    //  Is this good or bad below??
    @GetMapping(value = "/whiskies/{age}")
    public ResponseEntity<List<Whisky>> getAllWhiskiesByAge(@PathVariable int age) {
        return new ResponseEntity<>(whiskyRepository.findWhiskyByAge(age), HttpStatus.OK);
    }

    //  Seems to work for anything except macallan 25??
    @GetMapping(value = "whiskies/distillery")
    public ResponseEntity<List<Whisky>> getAllWhiskiesFromDistilleryByAge(@RequestParam(name = "distillery") String distilleryName, @RequestParam(name = "age") int age) {
        return new ResponseEntity<>(whiskyRepository.findByDistilleryNameAndAge(distilleryName, age), HttpStatus.OK);
    }

    @GetMapping(value = "whiskies/distilleries")
    public ResponseEntity<List<Whisky>> getAllWhiskiesByDistilleryRegion(@RequestParam(name = "region") String region) {
        return new ResponseEntity<>(whiskyRepository.findWhiskiesByDistilleryRegion(region), HttpStatus.OK);
    }
}
