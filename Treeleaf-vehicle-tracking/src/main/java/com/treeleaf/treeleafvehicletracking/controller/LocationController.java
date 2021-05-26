package com.treeleaf.treeleafvehicletracking.controller;

import com.treeleaf.treeleafvehicletracking.entity.Camera;
import com.treeleaf.treeleafvehicletracking.entity.Location;
import com.treeleaf.treeleafvehicletracking.service.location.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LocationController {


    @Autowired
    private LocationService locationService;


    @PostMapping(value = "/location")
    public ResponseEntity<Location> addNewLocation(@RequestBody Location location){
        return new ResponseEntity<>(locationService.addNewLocation(location), HttpStatus.OK);
    }

    @GetMapping(value = "/locations")
    public ResponseEntity<List<Location>> getAllLocation(){

        return new ResponseEntity<>(locationService.getAllLocation(), HttpStatus.OK);
    }

    @GetMapping(value = "/location/{locationid}")
    public ResponseEntity<Location> getLocationByID(@PathVariable("locationid")Long id){
        return new ResponseEntity<>(locationService.getLocationByID(id), HttpStatus.OK);
    }

    @GetMapping(value = "/location/{locationid}/cameras")
    public ResponseEntity<List<Camera>> getCamerasLocationByID(@PathVariable("locationid")Long id){
        return new ResponseEntity<>(locationService.getCamerasInLocationById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/location/{locationid}/camera")
    public ResponseEntity<Camera> addCameraByLocationId(@PathVariable("locationid")Long id,
                                                                @RequestBody Camera camera){
        return new ResponseEntity<>(locationService.addCameraInLocationId(id,camera),HttpStatus.OK);

    }
}
