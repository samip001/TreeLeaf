package com.treeleaf.treeleafvehicletracking.controller;

import com.treeleaf.treeleafvehicletracking.entity.Camera;
import com.treeleaf.treeleafvehicletracking.entity.Location;
import com.treeleaf.treeleafvehicletracking.service.camera.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class CameraController {


    @Autowired
    private CameraService cameraService;



    @GetMapping("/cameras")
    public ResponseEntity<List<Camera>> getAllCameras(){
        return new ResponseEntity<>(cameraService.getAllCamera(), HttpStatus.OK);
    }

    @PostMapping(value = "/camera")
    public ResponseEntity<Camera> addNewCamera(@RequestBody Camera camera){
        return new ResponseEntity<>(cameraService.addNewCamera(camera), HttpStatus.OK);
    }

    @GetMapping(value = "/camera/{cameraid}")
    public ResponseEntity<Camera> getCameraByID(@PathVariable("cameraid")Long id){
        return new ResponseEntity<>(cameraService.getCameraByID(id), HttpStatus.OK);
    }

    @GetMapping(value = "/camera/{cameraid}/locations")
    public ResponseEntity<List<Location>> getLocationByCameraID(@PathVariable("cameraid")Long id){
        return new ResponseEntity<>(cameraService.getAllLocationByCameraID(id), HttpStatus.OK);
    }

    @PutMapping(value = "/camera/{cameraid}/location")
    public ResponseEntity<Location> addLocationByCameraId(@PathVariable("cameraid")Long id,
                                                                @RequestBody Location location){
        return new ResponseEntity<>(cameraService.addLocationByCameraId(id,location),HttpStatus.OK);

    }


}

