package com.treeleaf.treeleafvehicletracking.service.location;

import com.treeleaf.treeleafvehicletracking.entity.Camera;
import com.treeleaf.treeleafvehicletracking.entity.Location;

import java.util.List;

public interface LocationService {

    Location addNewLocation(Location location);

    List<Location> getAllLocation();

    Location getLocationByID(Long id);

    Camera addCameraInLocationId(Long locationid,Camera camera);

    List<Camera> getCamerasInLocationById(Long locationId);
}
