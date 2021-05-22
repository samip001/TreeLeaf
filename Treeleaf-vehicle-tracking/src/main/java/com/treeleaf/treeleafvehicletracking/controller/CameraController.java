package com.treeleaf.treeleafvehicletracking.controller;

import com.treeleaf.treeleafvehicletracking.entity.Camera;
import com.treeleaf.treeleafvehicletracking.entity.Location;
import com.treeleaf.treeleafvehicletracking.error.exception.CameraIDNotFoundException;
import com.treeleaf.treeleafvehicletracking.repository.CameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CameraController {


    @Autowired
    private CameraRepository cameraRepository;



    @GetMapping("/cameras")
    public ResponseEntity<List<Camera>> getAllCameras(){
        List<Camera> cameras = new ArrayList<>();

        cameraRepository.findAll().forEach(camera -> cameras.add(camera));

        return new ResponseEntity<>(cameras, HttpStatus.OK);
    }

    @PostMapping(value = "/camera")
    public ResponseEntity<Camera> addNewCamera(@RequestBody Camera camera){
        Camera savedCamera = cameraRepository.save(camera);

        return new ResponseEntity<>(savedCamera, HttpStatus.OK);
    }

    @GetMapping(value = "/camera/{cameraid}")
    public ResponseEntity<Camera> getCameraByID(@PathVariable("cameraid")Long id){
        Camera camera = cameraRepository.findById(id)
                .orElseThrow(() -> new CameraIDNotFoundException(String.format("Camera ID of %d is not found", id)));

        return new ResponseEntity<>(camera, HttpStatus.OK);
    }

    @GetMapping(value = "/camera/{cameraid}/locations")
    public ResponseEntity<List<Location>> getLocationByCameraID(@PathVariable("cameraid")Long id){
        Camera camera = cameraRepository.findById(id)
                .orElseThrow(() -> new CameraIDNotFoundException(String.format("Camera ID of %d is not found", id)));


        return new ResponseEntity<>(camera.getLocations(), HttpStatus.OK);
    }


}

