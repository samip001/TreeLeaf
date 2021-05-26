package com.treeleaf.treeleafvehicletracking.service.vehiclemovement;

import com.treeleaf.treeleafvehicletracking.entity.VehicleMovement;

import java.util.List;

public interface VehicleMovementService {

    VehicleMovement addNewVehicleMovement(VehicleMovement vehicleMovement);

    List<VehicleMovement> getAllVehicleMovement();

    VehicleMovement getVehicleMovementByID(Long vehicleMovementId);

    List<VehicleMovement> getVehicleMovementByVehicleId(Long vehicleId);

    List<VehicleMovement> getVehicleMovementByCameraId(Long cameraId);

    List<VehicleMovement> getVehicleMovementByLocationId(Long locationId);


}


