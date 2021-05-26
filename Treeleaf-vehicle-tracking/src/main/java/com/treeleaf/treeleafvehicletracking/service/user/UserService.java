package com.treeleaf.treeleafvehicletracking.service.user;

import com.treeleaf.treeleafvehicletracking.dto.Login;
import com.treeleaf.treeleafvehicletracking.entity.User;

import java.util.List;

public interface UserService {


    List<User> getAllUsers();

    User getUserByUsername(String name);

    User login(Login login);
}
