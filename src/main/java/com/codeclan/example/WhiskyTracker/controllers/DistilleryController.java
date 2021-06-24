package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DistilleryController {

    @Autowired
    DistilleryRepository distilleryRepository;

    @GetMapping(value="/distilleries")
    public ResponseEntity <List<Distillery>> getDistilleryByRegion(@RequestParam(name = "region", required = false) String region){
        if (region != null) {
            return new ResponseEntity<>(distilleryRepository.findByRegion(region), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(distilleryRepository.findAll(), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/distillery/{id}")
    public ResponseEntity getDistillery(@PathVariable Long id){
        return new ResponseEntity<>(distilleryRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/distilleries/{age}")
    public ResponseEntity getDistilleryByWhiskiesAge(@PathVariable Integer age){
        return new ResponseEntity<>(distilleryRepository.findDistilleriesByWhiskiesAge(age), HttpStatus.OK);
    }
}
