package org.mautig.discord.auth.services;

import org.mautig.discord.auth.entities.AppUser;
import org.mautig.discord.auth.models.LoginDto;
import org.mautig.discord.auth.models.RegisterDto;

public interface UserService {
    AppUser save(AppUser user);

    AppUser register(RegisterDto registerDto);

    String authenticate(LoginDto loginDto);
}
