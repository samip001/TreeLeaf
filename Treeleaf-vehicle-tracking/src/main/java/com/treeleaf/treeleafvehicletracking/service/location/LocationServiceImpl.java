package com.treeleaf.treeleafvehicletracking.service.location;

import com.treeleaf.treeleafvehicletracking.entity.Camera;
import com.treeleaf.treeleafvehicletracking.entity.Location;
import com.treeleaf.treeleafvehicletracking.error.exception.LocationIDNotFoundException;
import com.treeleaf.treeleafvehicletracking.repository.CameraRepository;
import com.treeleaf.treeleafvehicletracking.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {


    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CameraRepository cameraRepository;

    @Override
    public Location addNewLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public List<Location> getAllLocation() {
        List<Location> locations = new ArrayList<>();
        locationRepository.findAll().forEach(locations::add);
        return locations;
    }

    @Override
    public Location getLocationByID(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new LocationIDNotFoundException(String.format("Location ID of %d is not found", id)));
    }

    @Override
    public Camera addCameraInLocationId(Long locationid,Camera camera) {
        Location location = locationRepository.findById(locationid)
                .orElseThrow(() -> new LocationIDNotFoundException(String.format("Location ID of %d is not found", locationid)));

        location.addCamera(camera);
        locationRepository.save(location);

        return camera;
    }

    @Override
    public List<Camera> getCamerasInLocationById(Long locationId) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new LocationIDNotFoundException(String.format("Location ID of %d is not found", locationId)));
        return location.getCameras();
    }
}
