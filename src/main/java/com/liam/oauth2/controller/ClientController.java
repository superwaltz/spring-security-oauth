package com.liam.oauth2.controller;

import com.liam.oauth2.service.ClientService;
import com.liam.oauth2.service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: liangzy
 * @date: 2019/03/15 上午10:58
 * @desc:
 */

@RestController
@RequestMapping
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private UserService userService;


    @GetMapping("/save")
    public String save(){
        clientService.save();
        return "Bob";
    }

    @GetMapping("/saveUser")
    public String saveUser(){
        userService.save();
        return "Bob";
    }
}
