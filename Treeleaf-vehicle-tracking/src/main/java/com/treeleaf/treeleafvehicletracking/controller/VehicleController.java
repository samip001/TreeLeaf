package com.treeleaf.treeleafvehicletracking.controller;

import com.treeleaf.treeleafvehicletracking.entity.Vehicle;
import com.treeleaf.treeleafvehicletracking.error.exception.VehicleIDNotFoundException;
import com.treeleaf.treeleafvehicletracking.repository.VehicleRepository;
import com.treeleaf.treeleafvehicletracking.service.vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VehicleController {


    @Autowired
    private VehicleService vehicleService;

    @PostMapping(value = "/vehicle")
    public ResponseEntity<Vehicle> addNewVehicle(@RequestBody Vehicle vehicle){

        return new ResponseEntity<>(vehicleService.addNewVehicle(vehicle), HttpStatus.OK);
    }

    @GetMapping(value = "/vehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicle(){
        return new ResponseEntity<>(vehicleService.getAllVehicle(), HttpStatus.OK);
    }

    @GetMapping(value = "/vehicle/{vehicle}")
    public ResponseEntity<Vehicle> getVehicleByID(@PathVariable("vehicle")Long id){
        return new ResponseEntity<>(vehicleService.getVehicleById(id), HttpStatus.OK);
    }


}
