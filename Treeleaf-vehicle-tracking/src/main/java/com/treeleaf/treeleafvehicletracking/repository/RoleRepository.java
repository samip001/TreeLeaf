package com.treeleaf.treeleafvehicletracking.repository;

import com.treeleaf.treeleafvehicletracking.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {
}
