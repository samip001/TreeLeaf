package com.treeleaf.treeleafvehicletracking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    private static final Logger log = LoggerFactory.getLogger(IndexController.class);


    // Testing purpose
    @RequestMapping(path= {"greetings","hello"},method= {RequestMethod.GET})
    public String greetings() {
        log.info("Greetings and hello path");
        return "Hello World";
    }

    @RequestMapping(path= "/",method= {RequestMethod.GET})
    public ResponseEntity<String> index(){
        return new ResponseEntity<>("I am Index", HttpStatus.OK);
    }
}
