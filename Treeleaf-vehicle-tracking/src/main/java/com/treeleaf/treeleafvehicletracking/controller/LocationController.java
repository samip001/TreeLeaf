package com.treeleaf.treeleafvehicletracking.controller;

import com.treeleaf.treeleafvehicletracking.entity.Camera;
import com.treeleaf.treeleafvehicletracking.entity.Location;
import com.treeleaf.treeleafvehicletracking.error.exception.LocationIDNotFoundException;
import com.treeleaf.treeleafvehicletracking.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LocationController {


    @Autowired
    private LocationRepository locationRepository;


    @PostMapping(value = "/location")
    public ResponseEntity<Location> addNewLocation(@RequestBody Location location){
        Location save = locationRepository.save(location);

        return new ResponseEntity<>(save, HttpStatus.OK);
    }

    @GetMapping(value = "/locations")
    public ResponseEntity<List<Location>> getAllLocation(){
        List<Location> locations = new ArrayList<>();

        locationRepository.findAll().forEach(location -> locations.add(location));

        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @GetMapping(value = "/location/{locationid}")
    public ResponseEntity<Location> getLocationByID(@PathVariable("locationid")Long id){
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new LocationIDNotFoundException(String.format("Location ID of %d is not found", id)));

        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @GetMapping(value = "/location/{locationid}/cameras")
    public ResponseEntity<List<Camera>> getCamerasLocationByID(@PathVariable("locationid")Long id){
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new LocationIDNotFoundException(String.format("Location ID of %d is not found", id)));


        return new ResponseEntity<>(location.getCameras(), HttpStatus.OK);
    }
}
