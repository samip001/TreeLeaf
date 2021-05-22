package com.treeleaf.treeleafvehicletracking.repository;

import com.treeleaf.treeleafvehicletracking.entity.Camera;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CameraRepository extends CrudRepository<Camera,Long> {
}
