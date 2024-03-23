package com.tez.dieticianpatientapp.controller;

import com.tez.dieticianpatientapp.dto.Requset.UserCreate;
import com.tez.dieticianpatientapp.dto.UserDto;
import com.tez.dieticianpatientapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("api/v1/users")
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserCreate userCreate){
        return userService.saveUser(userCreate.toUser());
    }
}
