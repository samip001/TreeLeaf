package com.treeleaf.treeleafvehicletracking.repository;

import com.treeleaf.treeleafvehicletracking.entity.Camera;
import com.treeleaf.treeleafvehicletracking.entity.Location;
import com.treeleaf.treeleafvehicletracking.entity.Vehicle;
import com.treeleaf.treeleafvehicletracking.entity.VehicleMovement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleMovementRepository extends CrudRepository<VehicleMovement,Long> {

    @Query("SELECT v FROM VehicleMovement v WHERE v.vehicle = :vehicle")
    List<VehicleMovement> getVehicleMovementByVehicle(@Param("vehicle")Vehicle vehicle);

    @Query("SELECT v FROM VehicleMovement v WHERE v.camera = :camera")
    List<VehicleMovement> getVehicleMovementByCamera(@Param("camera") Camera camera);

    @Query("SELECT v FROM VehicleMovement v WHERE v.location = :location")
    List<VehicleMovement> getVehicleMovementByLocation(@Param("location") Location location);
}
