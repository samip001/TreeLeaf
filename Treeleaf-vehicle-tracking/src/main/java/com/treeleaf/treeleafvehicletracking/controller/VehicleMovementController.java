package com.treeleaf.treeleafvehicletracking.controller;

import com.treeleaf.treeleafvehicletracking.report.GeneratePdfReport;
import com.treeleaf.treeleafvehicletracking.entity.Camera;
import com.treeleaf.treeleafvehicletracking.entity.Location;
import com.treeleaf.treeleafvehicletracking.entity.Vehicle;
import com.treeleaf.treeleafvehicletracking.entity.VehicleMovement;
import com.treeleaf.treeleafvehicletracking.error.exception.CameraIDNotFoundException;
import com.treeleaf.treeleafvehicletracking.error.exception.LocationIDNotFoundException;
import com.treeleaf.treeleafvehicletracking.error.exception.VehicleIDNotFoundException;
import com.treeleaf.treeleafvehicletracking.error.exception.VehicleMovementIDNotFoundException;
import com.treeleaf.treeleafvehicletracking.repository.CameraRepository;
import com.treeleaf.treeleafvehicletracking.repository.LocationRepository;
import com.treeleaf.treeleafvehicletracking.repository.VehicleMovementRepository;
import com.treeleaf.treeleafvehicletracking.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
public class VehicleMovementController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleMovementRepository vehicleMovementRepository;

    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private LocationRepository locationRepository;

    @PostMapping(value = "/movement")
    public ResponseEntity<VehicleMovement> addNewVehicleMovement(@RequestBody VehicleMovement vehicleMovement){
        VehicleMovement save = vehicleMovementRepository.save(vehicleMovement);

        return new ResponseEntity<>(save, HttpStatus.OK);
    }

    @GetMapping(value = "/vehiclemovements")
    public ResponseEntity<List<VehicleMovement>> getAllVehicleMovement(){
        List<VehicleMovement> vehicleMovements = new ArrayList<>();

        vehicleMovementRepository.findAll().forEach(vehicle -> vehicleMovements.add(vehicle));

        return new ResponseEntity<>(vehicleMovements, HttpStatus.OK);
    }

    @GetMapping(value = "/vehiclemovement/{vehiclemovementiid}")
    public ResponseEntity<VehicleMovement> getVehicleMovementByID(@PathVariable("vehiclemovementiid")Long id){
        VehicleMovement vehicleMovement = vehicleMovementRepository.findById(id)
                .orElseThrow(() -> new VehicleMovementIDNotFoundException(String.format("Vehicle Movement ID of %d is not found", id)));

        return new ResponseEntity<>(vehicleMovement, HttpStatus.OK);
    }

    @GetMapping(value = "/movement/{vehicleid}/vehicle")
    public ResponseEntity<List<VehicleMovement>> getVehicleMovementByVehicle(@PathVariable("vehicleid")Long id){

        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() ->
                new VehicleIDNotFoundException(String.format("Vehicle ID of %d is not found", id)));

        List<VehicleMovement> vehicleMovements = vehicleMovementRepository.getVehicleMovementByVehicle(vehicle);

        return new ResponseEntity<>(vehicleMovements, HttpStatus.OK);
    }

    @GetMapping(value = "/movement/{cameraid}/camera")
    public ResponseEntity<List<VehicleMovement>> getVehicleMovementByCamera(@PathVariable("cameraid")Long id){

        Camera camera = cameraRepository.findById(id).orElseThrow(() ->
                new CameraIDNotFoundException(String.format("Camera ID of %d is not found", id)));

        List<VehicleMovement> vehicleMovements = vehicleMovementRepository.getVehicleMovementByCamera(camera);

        return new ResponseEntity<>(vehicleMovements, HttpStatus.OK);
    }

    @GetMapping(value = "/movement/{locationid}/location")
    public ResponseEntity<List<VehicleMovement>> getVehicleMovementByLocation(@PathVariable("locationid")Long id){

        Location location = locationRepository.findById(id).orElseThrow(() ->
                new LocationIDNotFoundException(String.format("Location ID of %d is not found", id)));

        List<VehicleMovement> vehicleMovements = vehicleMovementRepository.getVehicleMovementByLocation(location);

        return new ResponseEntity<>(vehicleMovements, HttpStatus.OK);
    }

    @GetMapping(value = "/vehicle/{vehicleid}/movement/pdfreport", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generateVehicleMovementReport(@PathVariable("vehicleid") Long id) {

        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() ->
                new VehicleIDNotFoundException(String.format("Vehicle ID of %d is not found", id)));

        List<VehicleMovement> vehicleMovements = vehicleMovementRepository.getVehicleMovementByVehicle(vehicle);

        ByteArrayInputStream bis = GeneratePdfReport.vehicleMovementReport(vehicleMovements);

        // for default opening in browsers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=vehiclemovement.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
