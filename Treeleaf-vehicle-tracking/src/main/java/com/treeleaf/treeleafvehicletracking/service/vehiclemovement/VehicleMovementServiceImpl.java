package com.treeleaf.treeleafvehicletracking.service.vehiclemovement;

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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleMovementServiceImpl implements VehicleMovementService{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleMovementRepository vehicleMovementRepository;

    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public VehicleMovement addNewVehicleMovement(VehicleMovement vehicleMovement) {
        return vehicleMovementRepository.save(vehicleMovement);
    }

    @Override
    public List<VehicleMovement> getAllVehicleMovement() {
        List<VehicleMovement> vehicleMovements = new ArrayList<>();
        vehicleMovementRepository.findAll().iterator().forEachRemaining(vehicleMovements::add);
        return vehicleMovements;
    }

    @Override
    public VehicleMovement getVehicleMovementByID(Long vehicleMovementId) {
        return vehicleMovementRepository.findById(vehicleMovementId)
                .orElseThrow(() -> new VehicleMovementIDNotFoundException(String.format("Vehicle Movement ID of %d is not found", vehicleMovementId)));
    }

    @Override
    public List<VehicleMovement> getVehicleMovementByVehicleId(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() ->
                new VehicleIDNotFoundException(String.format("Vehicle ID of %d is not found", vehicleId)));

        return vehicleMovementRepository.getVehicleMovementByVehicle(vehicle);
    }

    @Override
    public List<VehicleMovement> getVehicleMovementByCameraId(Long cameraId) {

        Camera camera = cameraRepository.findById(cameraId).orElseThrow(() ->
                new CameraIDNotFoundException(String.format("Camera ID of %d is not found", cameraId)));

        return vehicleMovementRepository.getVehicleMovementByCamera(camera);

    }

    @Override
    public List<VehicleMovement> getVehicleMovementByLocationId(Long locationId) {
        Location location = locationRepository.findById(locationId).orElseThrow(() ->
                new LocationIDNotFoundException(String.format("Location ID of %d is not found", locationId)));

        return vehicleMovementRepository.getVehicleMovementByLocation(location);

    }
}
