package com.sigp.test.demo.controllers;

import com.sigp.test.demo.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController{

    private final UserService userService;

    public RestApiController(final UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users/{threshold}")
    public String[] getUsernames(@PathVariable("threshold") final int threshold){
        return userService.getUsernames(threshold);
    }

}
