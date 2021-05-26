package com.treeleaf.treeleafvehicletracking.service.vehicle;

import com.treeleaf.treeleafvehicletracking.entity.Vehicle;

import java.util.List;

public interface VehicleService {

    List<Vehicle> getAllVehicle();
    Vehicle addNewVehicle(Vehicle vehicle);
    Vehicle getVehicleById(Long vehicleId);

}
