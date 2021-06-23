package com.codeclan.example.WhiskyTracker.repositories;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WhiskyRepository extends JpaRepository<Whisky, Long> {

    List<Whisky> findByYear(Integer year);
    List<Whisky> findByDistilleryNameAndAge(String name, int age);
    List<Whisky> findWhiskyByAge(Integer age);
    List<Whisky> findWhiskiesByDistilleryRegion(String region);

}

