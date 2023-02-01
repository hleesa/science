package com.ftence.backend.controller;

import com.ftence.backend.dto.Response.LoginResponseDTO;
import com.ftence.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")  // 보안때문에 지워야 함
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{intraId}")
    public List<LoginResponseDTO> getUserInfo(@PathVariable String intraId) {
        return userService.getUserInfo(intraId);
    }


    @GetMapping("/")
}
