package org.mautig.discord.auth.controllers;

import org.mautig.discord.auth.models.LoginDto;
import org.mautig.discord.auth.models.RegisterDto;
import org.mautig.discord.auth.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    final private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public String login(@RequestBody LoginDto loginDto) {
        userService.authenticate(loginDto);
        return "HJI";
    }

    @PostMapping("register")
    public String register(@RequestBody RegisterDto registerDto) {
        return userService.register(registerDto).getEmail();
    }
}
