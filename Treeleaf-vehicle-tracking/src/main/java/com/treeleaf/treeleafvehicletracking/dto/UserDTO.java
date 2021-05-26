package com.treeleaf.treeleafvehicletracking.dto;

import com.treeleaf.treeleafvehicletracking.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String username;
    private Set<Role> roles = new HashSet<>();
}
