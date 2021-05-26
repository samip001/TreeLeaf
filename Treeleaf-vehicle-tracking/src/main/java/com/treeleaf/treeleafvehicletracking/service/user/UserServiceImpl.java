package com.treeleaf.treeleafvehicletracking.service.user;

import com.treeleaf.treeleafvehicletracking.dto.Login;
import com.treeleaf.treeleafvehicletracking.entity.Role;
import com.treeleaf.treeleafvehicletracking.entity.User;
import com.treeleaf.treeleafvehicletracking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);

        return users;
    }

    @Override
    public User getUserByUsername(String name) {
        return userRepository.getUserByUsername(name);

    }

    @Override
    public User login(Login login) {
        User saveduser = userRepository.getUserByUsername(login.getUsername());
        if(saveduser != null){
            boolean matched = BCrypt.checkpw(login.getPassword(), saveduser.getPassword());
            if(matched){
                boolean admin= false;
                for (Role role : saveduser.getRoles()) {
                    if (role.getName().equals("ROLE_ADMIN")) {
                        admin = true;
                    }
                }

                if(admin){
                    return saveduser;
                }
            }
        }
        return null;
    }
}
