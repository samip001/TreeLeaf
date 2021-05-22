package com.treeleaf.treeleafvehicletracking.controller;

import com.treeleaf.treeleafvehicletracking.entity.Role;
import com.treeleaf.treeleafvehicletracking.entity.User;
import com.treeleaf.treeleafvehicletracking.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser() {

        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(user -> users.add(user));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(path="/login", method=RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> login(@RequestBody User user){

        log.info(user.getUsername());
        log.info(user.getPassword());

        Map<String, Object> map = new HashMap<>();

        User saveduser = userRepository.getUserByUsername(user.getUsername());
        if(saveduser != null){
            boolean matched = BCrypt.checkpw(user.getPassword(), saveduser.getPassword());
            if(matched){

                boolean admin= false;
                for (Role role : saveduser.getRoles()) {
                    if (role.getName().equals("ROLE_ADMIN")) {
                        admin = true;
                    }
                }

                if(admin){
                    // making password empty
                    saveduser.setPassword(null);

                    map.put("user",saveduser);
                    map.put("message","Login Successfully");
                    map.put("status", HttpStatus.OK);
                    return new ResponseEntity<>(map,HttpStatus.OK);
                }else{
                    map.put("user",saveduser);
                    map.put("message","Unauthorized User");
                    map.put("status", HttpStatus.BAD_REQUEST);
                    return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
                }

            }else{
                map.put("status", HttpStatus.NOT_FOUND);
                map.put("message","Password mismatched. Try Again");
                return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);

            }

        }else{
            map.put("status", HttpStatus.NOT_FOUND);
            map.put("message", "Username not Found. Please Create new account from Signup form");
            return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path="/user/{name}",method=RequestMethod.GET)
    public ResponseEntity<User> get(@PathVariable("name" )String name) {
        User saveduser = userRepository.getUserByUsername(name);
        if(saveduser != null ) return new ResponseEntity<>(saveduser,HttpStatus.OK);
        else throw new UsernameNotFoundException("User Name Not Found for id :" +name);

    }
}
