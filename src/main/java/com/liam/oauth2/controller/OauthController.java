package com.liam.oauth2.controller;

import com.liam.oauth2.service.ClientService;
import com.liam.oauth2.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class OauthController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private UserService userService;


    @GetMapping("/t-oauth")
    public String saoauthve(){
        clientService.save();
        return "success";
    }

    @GetMapping("/t-authorize")
    public String authorize(){
        userService.save();
        return "success";
    }
}
