package com.treeleaf.treeleafvehicletracking.service.camera;

import com.treeleaf.treeleafvehicletracking.entity.Camera;
import com.treeleaf.treeleafvehicletracking.entity.Location;

import java.util.List;

public interface CameraService {

    Camera addNewCamera(Camera camera);

    List<Camera> getAllCamera();

    Camera getCameraByID(Long cameraId);

    List<Location> getAllLocationByCameraID(Long cameraId);

    Location addLocationByCameraId(Long cameraId,Location location);
}
