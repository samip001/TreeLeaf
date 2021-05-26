package com.treeleaf.treeleafvehicletracking.service.camera;

import com.treeleaf.treeleafvehicletracking.entity.Camera;
import com.treeleaf.treeleafvehicletracking.entity.Location;
import com.treeleaf.treeleafvehicletracking.error.exception.CameraIDNotFoundException;
import com.treeleaf.treeleafvehicletracking.repository.CameraRepository;
import com.treeleaf.treeleafvehicletracking.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CameraServiceImpl  implements CameraService {

    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Camera addNewCamera(Camera camera) {
        return cameraRepository.save(camera);
    }

    @Override
    public List<Camera> getAllCamera() {
        List<Camera> cameras = new ArrayList<>();
        cameraRepository.findAll().forEach(cameras::add);
        return cameras;
    }

    @Override
    public Camera getCameraByID(Long cameraId) {
        return cameraRepository.findById(cameraId)
                .orElseThrow(() -> new CameraIDNotFoundException(String.format("Camera ID of %d is not found", cameraId)));
    }

    @Override
    public List<Location> getAllLocationByCameraID(Long cameraId) {
        Camera camera = cameraRepository.findById(cameraId)
                .orElseThrow(() -> new CameraIDNotFoundException(String.format("Camera ID of %d is not found", cameraId)));
        return camera.getLocations();

    }

    @Override
    public Location addLocationByCameraId(Long cameraId, Location location) {

        Camera camera = cameraRepository.findById(cameraId)
                .orElseThrow(() -> new CameraIDNotFoundException(String.format("Camera ID of %d is not found", cameraId)));

        camera.addLocation(location);

        cameraRepository.save(camera);
        return location;
    }
}
