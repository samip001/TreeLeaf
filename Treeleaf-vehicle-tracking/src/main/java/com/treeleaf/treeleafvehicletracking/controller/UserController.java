package com.treeleaf.treeleafvehicletracking.controller;

import com.treeleaf.treeleafvehicletracking.dto.Login;
import com.treeleaf.treeleafvehicletracking.dto.UserDTO;
import com.treeleaf.treeleafvehicletracking.entity.User;
import com.treeleaf.treeleafvehicletracking.service.user.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUser() {
        List<UserDTO> userDTOS = userService.getAllUsers()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @RequestMapping(path="/login", method=RequestMethod.POST)
    public ResponseEntity<UserDTO> login(@RequestBody Login login){
        log.info("user",login.getUsername());
        log.info("password",login.getPassword());


        User validUser = userService.login(login);

        if(validUser != null){
            UserDTO userDTO = modelMapper.map(validUser, UserDTO.class);
            return new ResponseEntity<>(userDTO,HttpStatus.OK);
        }

        return new ResponseEntity<>(new UserDTO(),HttpStatus.BAD_REQUEST);

//        Map<String, Object> map = new HashMap<>();
//
//        User saveduser = userRepository.getUserByUsername(user.getUsername());
//        if(saveduser != null){
//            boolean matched = BCrypt.checkpw(user.getPassword(), saveduser.getPassword());
//            if(matched){
//
//                boolean admin= false;
//                for (Role role : saveduser.getRoles()) {
//                    if (role.getName().equals("ROLE_ADMIN")) {
//                        admin = true;
//                    }
//                }
//
//                if(admin){
//                    // making password empty
//                    saveduser.setPassword("validated password");
//
//                    map.put("user",saveduser);
//                    map.put("message","Login Successfully");
//                    map.put("status", HttpStatus.OK);
//                    return new ResponseEntity<>(map,HttpStatus.OK);
//                }else{
//                    map.put("message","Unauthorized User");
//                    map.put("status", HttpStatus.BAD_REQUEST);
//                    return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
//                }
//
//            }else{
//                map.put("status", HttpStatus.NOT_FOUND);
//                map.put("message","Password mismatched. Try Again");
//                return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
//
//            }
//
//        }else{
//            map.put("status", HttpStatus.NOT_FOUND);
//            map.put("message", "Username not Found. Please Create new account from Signup form");
//            return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
//        }
    }

    @RequestMapping(path="/user/{name}",method=RequestMethod.GET)
    public ResponseEntity<UserDTO> get(@PathVariable("name" )String name) {
        User saveduser = userService.getUserByUsername(name);
        UserDTO userDTO = modelMapper.map(saveduser, UserDTO.class);
        return new ResponseEntity<>(userDTO,HttpStatus.OK);

    }
}
