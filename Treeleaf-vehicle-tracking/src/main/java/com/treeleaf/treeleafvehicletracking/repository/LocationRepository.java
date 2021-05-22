package com.treeleaf.treeleafvehicletracking.repository;

import com.treeleaf.treeleafvehicletracking.entity.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location,Long> {
}
