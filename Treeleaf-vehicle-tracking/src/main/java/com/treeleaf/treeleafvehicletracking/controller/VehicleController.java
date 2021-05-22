package com.treeleaf.treeleafvehicletracking.controller;

import com.treeleaf.treeleafvehicletracking.entity.Vehicle;
import com.treeleaf.treeleafvehicletracking.error.exception.VehicleIDNotFoundException;
import com.treeleaf.treeleafvehicletracking.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VehicleController {


    @Autowired
    private VehicleRepository vehicleRepository;

    @PostMapping(value = "/vehicle")
    public ResponseEntity<Vehicle> addNewVehicle(@RequestBody Vehicle vehicle){
        Vehicle save = vehicleRepository.save(vehicle);

        return new ResponseEntity<>(save, HttpStatus.OK);
    }

    @GetMapping(value = "/vehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicle(){
        List<Vehicle> vehicles = new ArrayList<>();

        vehicleRepository.findAll().forEach(vehicle -> vehicles.add(vehicle));

        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping(value = "/vehicle/{vehicle}")
    public ResponseEntity<Vehicle> getVehicleByID(@PathVariable("vehicle")Long id){
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleIDNotFoundException(String.format("Vehicle ID of %d is not found", id)));

        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }


}
