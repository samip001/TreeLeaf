package com.treeleaf.treeleafvehicletracking.controller;

import com.treeleaf.treeleafvehicletracking.entity.VehicleMovement;
import com.treeleaf.treeleafvehicletracking.report.GeneratePdfReport;
import com.treeleaf.treeleafvehicletracking.service.vehiclemovement.VehicleMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
public class VehicleMovementController {


    @Autowired
    private VehicleMovementService vehicleMovementService;


    @PostMapping(value = "/vehiclemovement")
    public ResponseEntity<VehicleMovement> addNewVehicleMovement(@RequestBody VehicleMovement vehicleMovement){
        return new ResponseEntity<>(vehicleMovementService.addNewVehicleMovement(vehicleMovement), HttpStatus.OK);
    }

    @GetMapping(value = "/vehiclemovements")
    public ResponseEntity<List<VehicleMovement>> getAllVehicleMovement(){
        return new ResponseEntity<>(vehicleMovementService.getAllVehicleMovement(), HttpStatus.OK);
    }

    @GetMapping(value = "/vehiclemovement/{vehiclemovementiid}")
    public ResponseEntity<VehicleMovement> getVehicleMovementByID(@PathVariable("vehiclemovementiid")Long id){
        return new ResponseEntity<>(vehicleMovementService.getVehicleMovementByID(id), HttpStatus.OK);
    }

    @GetMapping(value = "/vehiclemovement/{vehicleid}/vehicle")
    public ResponseEntity<List<VehicleMovement>> getVehicleMovementByVehicle(@PathVariable("vehicleid")Long id){
        return new ResponseEntity<>(vehicleMovementService.getVehicleMovementByVehicleId(id), HttpStatus.OK);
    }

    @GetMapping(value = "/vehiclemovement/{cameraid}/camera")
    public ResponseEntity<List<VehicleMovement>> getVehicleMovementByCamera(@PathVariable("cameraid")Long id){
        return new ResponseEntity<>(vehicleMovementService.getVehicleMovementByCameraId(id), HttpStatus.OK);
    }

    @GetMapping(value = "/vehiclemovement/{locationid}/location")
    public ResponseEntity<List<VehicleMovement>> getVehicleMovementByLocation(@PathVariable("locationid")Long id){

        return new ResponseEntity<>(vehicleMovementService.getVehicleMovementByLocationId(id), HttpStatus.OK);
    }

    @GetMapping(value = "/vehiclemovement/{vehicleid}/movement/pdfreport", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generateVehicleMovementReport(@PathVariable("vehicleid") Long id) {

        List<VehicleMovement> vehicleMovements = vehicleMovementService.getVehicleMovementByVehicleId(id);
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
