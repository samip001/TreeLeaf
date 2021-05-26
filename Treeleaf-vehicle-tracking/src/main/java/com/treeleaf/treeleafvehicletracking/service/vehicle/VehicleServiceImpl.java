package com.treeleaf.treeleafvehicletracking.service.vehicle;

import com.treeleaf.treeleafvehicletracking.entity.Vehicle;
import com.treeleaf.treeleafvehicletracking.error.exception.VehicleIDNotFoundException;
import com.treeleaf.treeleafvehicletracking.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {


    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> getAllVehicle() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicleRepository.findAll().forEach(vehicles::add);
        return vehicles;
    }

    @Override
    public Vehicle addNewVehicle(Vehicle vehicle) {
        return  vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle getVehicleById(Long vehicleId) {
        return vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new VehicleIDNotFoundException(String.format("Vehicle ID of %d is not found", vehicleId)));
    }
}
