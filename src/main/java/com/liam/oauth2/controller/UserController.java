package com.liam.oauth2.controller;

import com.liam.oauth2.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/t-save")
    public String save(){
        userService.save();
        return "success";
    }

    @GetMapping("/t-delete")
    public String delete(){
        userService.delete(1);
        return "success";
    }
}
