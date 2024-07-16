package org.mautig.discord.auth.services.impl;

import org.mautig.discord.auth.entities.AppUser;
import org.mautig.discord.auth.models.LoginDto;
import org.mautig.discord.auth.models.RegisterDto;
import org.mautig.discord.auth.repositories.UserRepository;
import org.mautig.discord.auth.services.JwtService;
import org.mautig.discord.auth.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;
    final private JwtService jwtService;
    final private PasswordEncoder passwordEncoder;
    final private AuthenticationManager authenticationManager;

    public UserServiceImpl(
            UserRepository userRepository,
            JwtService jwtService,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AppUser save(AppUser user) {
        return userRepository.save(user);
    }

    @Override
    public AppUser register(RegisterDto registerDto) {
        var existUser = userRepository.findByEmail(registerDto.getEmail());
        if (existUser.isPresent()) {
            throw new IllegalStateException("Email already exist!");
        }
        var newUser = new AppUser();
        newUser.setEmail(registerDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        return userRepository.save(newUser);
    }

    @Override
    public String authenticate(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        var token = "";
        return token;
    }

}
